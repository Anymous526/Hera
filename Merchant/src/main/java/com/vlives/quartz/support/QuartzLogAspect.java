package com.vlives.quartz.support;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Service;

import com.vlives.core.exception.BusinessException;
import com.vlives.quartz.domain.TimingTaskLog;
import com.vlives.quartz.manager.TimingTaskLogManager;
import com.vlives.util.ClassUtils;

/**
 * AOP 面向切面定时任务日志记录功能
 * 
 * @author MrXu
 * 
 */
@Aspect
@Service
@Lazy(false)
public class QuartzLogAspect {
	@Resource(name="scheduler")
	private Scheduler scheduler;
	private static final Log LOG = LogFactory.getLog(QuartzLogAspect.class);
	@Autowired
	private TimingTaskLogManager timingTaskTestManager;
	/**
	 * 记录定时任务的日志
	 * @author jianguo.xu
	 * @param joinPoint
	 * @param quartzLog
	 * @throws Throwable
	 */
	@Around("@annotation(quartzLog)")
	public void executeLog(ProceedingJoinPoint joinPoint, QuartzLog quartzLog) throws Throwable {
		LOG.info("start scheduler task : " + quartzLog.name().getDesc());
		TimingTaskLog timingTaskLog = timingTaskTestManager.create(quartzLog.name());
		try {
			joinPoint.proceed();
			timingTaskTestManager.result(timingTaskLog, true, "success");
		} catch (Exception e) {
			timingTaskTestManager.result(timingTaskLog, false, e.getMessage());
			LOG.error("process scheduler task error.",e);
		}
		LOG.info("end scheduler task");
	}
	/**
	 * 项目启动时，检查定时任务是否配置了 QuartzLog 如果没有配置则抛异常
	 * @author jianguo.xu
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void checkQuartzLogConfig() throws Exception {
		List<Class<?>> classes = ClassUtils.getClassPathClasses("", true);
		List<JobDetail> jobDetails = loadJobDetails();
		for(JobDetail jobDetail : jobDetails) {
			JobDataMap jobDataMap = jobDetail.getJobDataMap();
			Set<String> set = jobDataMap.keySet();
			for(String key : set) {
				MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = (MethodInvokingJobDetailFactoryBean) jobDataMap.get(key);
				assertQuartzLogConfig(classes, jobDetailFactoryBean.getTargetObject(), jobDetailFactoryBean.getTargetMethod());
			}
		} 
	}
	
	private void assertQuartzLogConfig(List<Class<?>> classes,Object targetObject,String methodStr) throws BusinessException {
		String resoureceObjName = targetObject.toString().split("@")[0];
		for(Class<?> clazz : classes) {
			if(!clazz.getName().equals(resoureceObjName)) continue;
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods) {
				if(method.getName().endsWith(methodStr))  {
					if(method.getAnnotation(QuartzLog.class)==null) {
						throw new BusinessException("scheduler must bind QuartzLog annotation in ["+resoureceObjName+"."+methodStr+"]");
					}
					return;
				}
			}
		}
		throw new BusinessException("scheduler must bind QuartzLog annotation in ["+resoureceObjName+"."+methodStr+"]");
	}
	
	private List<JobDetail> loadJobDetails() throws SchedulerException {
		List<JobDetail> jobDetails = new ArrayList<JobDetail>();
		String[] jobGroupNames = scheduler.getJobGroupNames();
		if(jobGroupNames == null||jobGroupNames.length == 0) return jobDetails;
		for(String groupName : jobGroupNames) {
			String[] jobNames = scheduler.getJobNames(groupName);
			if(jobNames == null||jobNames.length == 0) continue;
			for(String jobName : jobNames) {
				jobDetails.add(scheduler.getJobDetail(jobName, groupName));
			}
		}
		return jobDetails;
	}
}
