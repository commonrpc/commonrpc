/**
 * 
 */
package com.cross.plateform.common.rpc.server.filter;

import java.lang.reflect.Method;

/**
 * @author liubing1
 *
 */
public interface RpcFilter {
	
	/**
	 * 拦截服务端开始前处理
	 * @param method
	 * @param processor
	 * @param requestObjects
	 * @return
	 */
	public boolean doBeforeRequest(Method method,Object processor,Object[] requestObjects);
	/**
	 * 拦截器服务端后处理
	 * @param processor
	 * @return
	 */
	public boolean doAfterRequest(Object processor);
	
	
}
