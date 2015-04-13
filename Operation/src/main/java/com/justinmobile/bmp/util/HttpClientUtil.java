package com.justinmobile.bmp.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.justinmobile.bmp.cloudboss.caller.CloudBossCaller;

/**
 * HTTP接口工具类
 * @author wei.zhang
 *
 */
public class HttpClientUtil {
	
	private static final Log LOG = LogFactory.getLog("interfacelogfile");
	
	private static final String CLOUDBOSS_COOKIE_NAME = CloudBossCaller.getCloudbossCookieName();
	private static final String CLOUDBOSS_COOKIE_VALUE = CloudBossCaller.getCloudbossCookieValue();
	private static final String CLOUDBOSS_COOKIE_PATH = CloudBossCaller.getCloudbossCookiePath();
	private static final String CLOUDBOSS_COOKIE_DOMAIN = CloudBossCaller.getCloudbossCookieDomain();
	

	public static enum MethodType {
		GET {
			@Override
			HttpRequestBase getHttpRequest(String url, List<NameValuePair> params) {
				return (HttpRequestBase) (new HttpGet(url + "?" + URLEncodedUtils.format(params, "UTF-8")));
			}
		},
		POST {
			@Override
			HttpRequestBase getHttpRequest(String url, List<NameValuePair> params) throws UnsupportedEncodingException {
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				return (HttpRequestBase)httpPost;
			}
		},
		PUT {
			@Override
			HttpRequestBase getHttpRequest(String url, List<NameValuePair> params) throws UnsupportedEncodingException {
				HttpPut httpPut = new HttpPut(url);
				httpPut.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				return (HttpRequestBase)httpPut;
			}
		},
		DELETE {
			@Override
			HttpRequestBase getHttpRequest(String url, List<NameValuePair> params) {
				return (HttpRequestBase) (new HttpDelete(url + "?" + URLEncodedUtils.format(params, "UTF-8")));
			}
		};

		abstract HttpRequestBase getHttpRequest(String url, List<NameValuePair> params)  throws UnsupportedEncodingException;
	}

	/**
	 * 按指定的方法发送HTTP请求并获得响应
	 * @param url 请求目标地址
	 * @param headerMap 头信息
	 * @param paramMap 请求的参数
	 * @param methodType 指定的请求方法，GET/POST/PUT/DELETE
	 * @return HTTP响应
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	/**
	 * @param url
	 * @param headerMap
	 * @param paramMap
	 * @param methodType
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String request(String url,
			Map<String, Object> headerMap, Map<String, Object> paramMap,
			MethodType methodType, BasicClientCookie cookie) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		// security cookie set
		HttpClientParams.setCookiePolicy(httpclient.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);
		CookieStore cookieStore = new BasicCookieStore();
		cookieStore.addCookie(cookie);
		httpclient.setCookieStore(cookieStore);
		//
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (paramMap != null) {
			Set<String> keySets = paramMap.keySet();
			for (String key : keySets) {
				Object val = paramMap.get(key);
				if( val == null || "".equals(val)){
					continue;
				} else if ( val.getClass().isArray() ) {
					for(Object obj : (Object[])val ){
						params.add(new BasicNameValuePair(key, obj.toString()));
					}
				} else {
					params.add(new BasicNameValuePair(key, val.toString()));
				}
			}
		}
		//
		HttpRequestBase httpRequestBase = methodType.getHttpRequest(url, params);
		
		//
		if (headerMap != null) {
			Set<String> keySets = headerMap.keySet();
			for (String key : keySets) {
				Object val = headerMap.get(key);
				if( val == null ){
					continue;
				}else if( val.getClass().isArray() ){
					for( Object elVar : (Object[])val ){
						httpRequestBase.setHeader(key, elVar.toString());
					}
				}else{
					httpRequestBase.setHeader(key, val.toString());
				}
			}
		}
		//
		try {
			log(httpRequestBase);
			HttpResponse response = httpclient.execute(httpRequestBase);
			log(response);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
//			LOG.info("RESPONSE.ENTITY  -- " + result + " -- ");
			return result;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * 调用request方法获得JSON格式的响应
	 * @return JSON响应
	 * @throws ClientProtocolException 
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject getResponseByJson(String url,
			Map<String, Object> headerMap, Map<String, Object> paramMap,
			MethodType methodType, BasicClientCookie cookie) throws ClientProtocolException, IOException  {
		return JSONObject.fromObject( request(url, headerMap, paramMap, methodType, cookie) );
	}

	/**
	 * 调用request方法获得JSON格式的响应
	 * @return JSON响应
	 * @throws ClientProtocolException 
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject getResponseByFile(String url,
			Map<String, Object > headerMap, Map<String, Object> paramMap, File file) throws ClientProtocolException, IOException  {
		return JSONObject.fromObject( requestFile(url, headerMap, paramMap , file) );
	}

	/**
	 * 按指定的方法发送HTTP请求并获得响应
	 * @param url 请求目标地址
	 * @param headerMap 头信息
	 * @param paramMap 请求的参数
	 * @param methodType 指定的请求方法，GET/POST/PUT/DELETE
	 * @return HTTP响应
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String requestFile(String url,Map<String, Object> headerMap, Map<String, Object> paramMap, File file) throws ClientProtocolException, IOException {
		String result = "";
		HttpClient client = new HttpClient();
		HttpState state = new HttpState();
		Date expiryDate = new Date();
		expiryDate.setTime(expiryDate.getTime() + 24*60*60*1000 );
		Cookie c = new Cookie();
		c.setPath(CLOUDBOSS_COOKIE_PATH);
		c.setDomain(CLOUDBOSS_COOKIE_DOMAIN);
		c.setName(CLOUDBOSS_COOKIE_NAME);
		c.setValue(CLOUDBOSS_COOKIE_VALUE);
		c.setExpiryDate(expiryDate);
		state.addCookie(c);
		client.setState(state);
        PostMethod postMethod = new PostMethod(url);
        
        List<StringPart> paramList = new ArrayList<StringPart>();
		if (paramMap != null) {
			Set<String> keySets = paramMap.keySet();
			for (String key : keySets) {
				Object val = paramMap.get(key);
				if( val == null || "".equals(val)){
					continue;
				}else if( val.getClass().isArray() ){
					for( Object elVar : (Object[])val ){
						if(!"".equals(elVar.toString())){
							paramList.add(new StringPart(key, elVar.toString(),"UTF-8"));
						}
					}
				}else{
					paramList.add(new StringPart(key, val.toString(),"UTF-8"));
				}
			}
		}
        Part[] part = new Part[paramList.size()+1];
        part[0] = new  FilePart("importFile", file);
		for(int i=1;i<=paramList.size();i++){
			part[i] = paramList.get(i-1);
		}
        MultipartRequestEntity mrp = new MultipartRequestEntity(part , postMethod
                .getParams());
    
        postMethod.setRequestEntity(mrp);
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        int httpStat  =  client.executeMethod(postMethod);
  	    if (httpStat == HttpStatus.SC_OK)
	    {
  	    	result = postMethod.getResponseBodyAsString();
//			LOG.info("[" + Calendar.getInstance().getTime() + "]RESPONSE.ENTITY  -- " + result + " -- ");
	    }
	    else
	    {
	    	if(postMethod.getResponseBodyAsString().equals(""))
	    		result = "{\"desc\":\"操作失败\",\"success\":false}";
	    	else
	    		result = postMethod.getResponseBodyAsString();
	    }
		return result;

	}
	
	private static void log(HttpRequestBase request){
		String msg = request.getRequestLine().toString() + " -- ";
		msg = msg + "Headers : { ";
		Header[] headers = request.getAllHeaders();
		for( Header header : headers){
			msg = msg + "{" + header.toString() + "}";
		}
		msg = msg + " }";
		LOG.info("[" + Calendar.getInstance().getTime() + "]REQUEST -- " + msg + " -- ");
	}

	private static void log(HttpResponse response){
		LOG.info("[" + Calendar.getInstance().getTime() + "]RESPONSE  -- " + response.toString() + " -- ");
	}

}
