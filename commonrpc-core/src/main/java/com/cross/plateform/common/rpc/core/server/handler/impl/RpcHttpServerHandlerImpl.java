/**
 * 
 */
package com.cross.plateform.common.rpc.core.server.handler.impl;

import java.util.Map;

import com.cross.plateform.common.rpc.core.server.handler.AbstractRpcHttpServerHandler;
import com.cross.plateform.common.rpc.core.server.service.bean.RpcHttpBean;
import com.cross.plateform.common.rpc.core.server.service.bean.RpcRouteInfo;
import com.cross.plateform.common.rpc.core.server.service.factory.CommonRpcRouteServiceFactory;
import com.cross.plateform.common.rpc.server.filter.RpcFilter;

/**
 * @author liubing1
 *
 */
public class RpcHttpServerHandlerImpl extends AbstractRpcHttpServerHandler {
	public static final int TYPE = 0;
	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.server.handler.RpcServerHandler#registerProcessor(java.lang.String, java.lang.Object, com.cross.plateform.common.rpc.server.filter.RpcFilter)
	 */
	@Override
	public void registerProcessor(String instanceName, Object instance,
			RpcFilter rpcFilter) {
		// TODO Auto-generated method stub
		if(instance instanceof RpcHttpBean ){
			RpcHttpBean rpcHttpBean=(RpcHttpBean) instance;
			CommonRpcRouteServiceFactory.getCommonRpcRouteService().registerProcessor(instanceName, rpcHttpBean.getObject(), rpcFilter, rpcHttpBean.getHttpType(), rpcHttpBean.getReturnType());
		}
	}

	@Override
	public RpcRouteInfo isRouteInfos(String route, String methodType,
			Map<String, Object> params) throws Exception{
		// TODO Auto-generated method stub
		return CommonRpcRouteServiceFactory.getCommonRpcRouteService().isRouteInfos(route, methodType, params);
	}
	
	@Override
	public Object methodInvoke(RpcRouteInfo routeInfo) throws Exception {
		// TODO Auto-generated method stub
		return CommonRpcRouteServiceFactory.getCommonRpcRouteService().methodInvoke(routeInfo);
	}
	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.server.handler.RpcServerHandler#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		CommonRpcRouteServiceFactory.getCommonRpcRouteService().clear();
	}

}
