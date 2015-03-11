/**
 * 
 */
package com.cross.plateform.common.rpc.http.netty4.spring.config.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.cross.plateform.common.rpc.core.server.service.bean.RpcHttpBean;
import com.cross.plateform.common.rpc.http.netty4.server.CommonRpcHttpServer;
import com.cross.plateform.common.rpc.server.filter.RpcFilter;

/**
 * @author liubing1
 *
 */
public class CommonRpcHttpService implements ApplicationContextAware, ApplicationListener{
	
	private String projectname;
	
	private String ref;//服务类bean value
	
	private String filterRef;//拦截器类
	
	/**
	 * POST,GET,HEAD,PUT,DELETE
	 */
	private String httpType;
	
	/**
	 * Type:
	 * html,json,xml
	 */
	private String returnType;
	
	private ApplicationContext applicationContext;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		Object object=applicationContext.getBean(ref);
		if(filterRef==null){
			RpcFilter rpcFilter=(RpcFilter) applicationContext.getBean(filterRef);
			RpcHttpBean rpcHttpBean=new RpcHttpBean(object, httpType, returnType);
			CommonRpcHttpServer.getInstance().registerProcessor(projectname, rpcHttpBean, rpcFilter);
		}else{
			RpcHttpBean rpcHttpBean=new RpcHttpBean(object, httpType, returnType);
			CommonRpcHttpServer.getInstance().registerProcessor(projectname, rpcHttpBean, null);
		}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext=applicationContext;
	}

	/**
	 * @return the projectname
	 */
	public String getProjectname() {
		return projectname;
	}

	/**
	 * @param projectname the projectname to set
	 */
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * @return the filterRef
	 */
	public String getFilterRef() {
		return filterRef;
	}

	/**
	 * @param filterRef the filterRef to set
	 */
	public void setFilterRef(String filterRef) {
		this.filterRef = filterRef;
	}

	/**
	 * @return the httpType
	 */
	public String getHttpType() {
		return httpType;
	}

	/**
	 * @param httpType the httpType to set
	 */
	public void setHttpType(String httpType) {
		this.httpType = httpType;
	}

	/**
	 * @return the returnType
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * @param returnType the returnType to set
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	
}
