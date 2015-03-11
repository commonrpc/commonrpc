/**
 * 
 */
package com.cross.plateform.common.rpc.core.server.handler;

import java.util.Map;

import com.cross.plateform.common.rpc.core.server.service.bean.RpcRouteInfo;

/**
 * @author liubing1
 *
 */
public abstract class AbstractRpcHttpServerHandler implements RpcServerHandler {
	
	public abstract RpcRouteInfo isRouteInfos(String route, String methodType,Map<String, Object> params)throws Exception;

	
	public abstract Object methodInvoke(RpcRouteInfo routeInfo) throws Exception;
}
