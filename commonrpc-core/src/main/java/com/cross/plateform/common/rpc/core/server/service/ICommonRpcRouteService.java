/**
 * 
 */
package com.cross.plateform.common.rpc.core.server.service;

import java.util.Map;
import com.cross.plateform.common.rpc.core.server.service.bean.RpcRouteInfo;
import com.cross.plateform.common.rpc.server.filter.RpcFilter;
/**
 * @author liubing1
 *
 */
public interface ICommonRpcRouteService {
	
	/**
	 * 判断请求地址是否包含在路由表内，有则返回对应的实体
	 * 如果有，设置参数;没有不设置，返回null
	 * @param route
	 * @param methodType
	 * @param parmas
	 * @return
	 * @throws Exception
	 */
	public RpcRouteInfo isRouteInfos(String route,String methodType,Map<String, Object> parmas) throws Exception;

	/**
	 * 根据参数执行对应的方法
	 * @param routeInfo
	 * @return
	 * @throws Exception
	 */
	public Object methodInvoke(RpcRouteInfo routeInfo) throws Exception;
	
	/**
	 * 注册服务
	 * @param instanceName
	 * @param instance
	 */
	public void registerProcessor(String projectname, Object instance,RpcFilter rpcFilter,String httpType,String returnType);
	
	public void clear();
}	
