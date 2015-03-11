/**
 * 
 */
package com.cross.plateform.common.rpc.core.server.service.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

import com.cross.plateform.common.rpc.server.filter.RpcFilter;

/**
 * @author liubing1
 *
 */
public class RpcRouteInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8853603569468133779L;
	
	/**
	 * url
	 */
	private String route;
	
	/**
	 * 方法名称
	 */
	private String method;
	
	/**
	 * 类型
	 * post,get,head,put,delete
	 */
	private String httpType;

	/**
	 * 参数
	 */
	private Map<String, Object> params;
	
	/**
	 * 实现类
	 */
	private Class<?> objCls;//
	
	/**
	 * 方法
	 */
	private Method[] methods;
	
	/**
	 * 返回类型 
	 * text/plain json
	 */
	private String returnType;
	
	/**
	 * 过滤器
	 */
	private RpcFilter rpcFilter;
	
	public RpcRouteInfo() {
		super();
	}

	/**
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(String route) {
		this.route = route;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
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
	 * @return the params
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	/**
	 * @return the objCls
	 */
	public Class<?> getObjCls() {
		return objCls;
	}

	/**
	 * @param objCls the objCls to set
	 */
	public void setObjCls(Class<?> objCls) {
		this.objCls = objCls;
	}

	/**
	 * @return the methods
	 */
	public Method[] getMethods() {
		return methods;
	}

	/**
	 * @param methods the methods to set
	 */
	public void setMethods(Method[] methods) {
		this.methods = methods;
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
	 * @return the rpcFilter
	 */
	public RpcFilter getRpcFilter() {
		return rpcFilter;
	}

	/**
	 * @param rpcFilter the rpcFilter to set
	 */
	public void setRpcFilter(RpcFilter rpcFilter) {
		this.rpcFilter = rpcFilter;
	}
	
	
}
