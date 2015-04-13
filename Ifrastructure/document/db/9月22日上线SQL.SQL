insert into resources  (id,path,description) values(18,'/manager/coupon/create/**','优惠券券创建资源');
insert into resources  (id,path,description) values(19,'/manager/coupon/ploy/**','优惠券活动管理');
insert into resources  (id,path,description) values(20,'/manager/coupon/coupon/**','优惠券查询');
insert into resources  (id,path,description) values(21,'/manager/report/coupon/**','优惠券报表资源');

insert into ROLE(id,role_group,name,entry_index,logo,description,SHORT_CUT) 
values(18,8,'发布优惠券活动','/manager/coupon/create/create_one.htm','/manager/image/dev/T_18.jpg','发布优惠券活动',1);
insert into ROLE(id,role_group,name,entry_index,logo,description,SHORT_CUT) 
values(19,8,'优惠券活动管理','/manager/coupon/ploy/list.htm','/manager/image/dev/T_03.jpg','优惠券活动管理',0);
insert into ROLE(id,role_group,name,entry_index,logo,description,SHORT_CUT) 
values(20,8,'优惠券查询','/manager/coupon/coupon/list.htm','/manager/image/dev/T_03.jpg','优惠券查询',0);
insert into ROLE(id,role_group,name,entry_index,logo,description,SHORT_CUT) 
values(21,7,'电子券报表','/manager/report/coupon/list.htm','/manager/image/dev/T_03.jpg','电子券报表',0);


insert into role_resource (id,role_id,resource_id)values(18,18,18);
insert into role_resource (id,role_id,resource_id)values(19,19,19);
insert into role_resource (id,role_id,resource_id)values(20,20,20);
insert into role_resource (id,role_id,resource_id)values(21,21,21);

/**
 * 查询需要添加优惠券权限的帐号
 *  insert into manager_role (id,employee_id,role_id)values(seq_manager_role.nextval,?,?);
 */
 select distinct(employee_id) from MANAGER_ROLE where employee_id not in(
  select employee_id from role r left join MANAGER_ROLE m on r.id = m.role_id 
  where r.id=18
  ) 
  
 