/**
 * 
 */
package com.cross.plateform.common.rpc.core.server.service.factory;
import com.cross.plateform.common.rpc.core.server.service.ICommonRpcRouteService;
import com.cross.plateform.common.rpc.core.server.service.impl.CommonRpcRouteServiceImpl;

/**
 * @author liubing1
 *
 */
public class CommonRpcRouteServiceFactory {
	
	private static ICommonRpcRouteService[] commonRpcRouteServices=new ICommonRpcRouteService[1];
	
	static{
		registerRouteService(CommonRpcRouteServiceImpl.TYPE, new CommonRpcRouteServiceImpl());
	}
	
	private static void registerRouteService(int type,ICommonRpcRouteService commonRpcRouteService){
		if(type > commonRpcRouteServices.length){
			ICommonRpcRouteService[] newServerHandlers = new ICommonRpcRouteService[type + 1];
			System.arraycopy(commonRpcRouteServices, 0, newServerHandlers, 0, commonRpcRouteServices.length);
			commonRpcRouteServices = newServerHandlers;
		}
		commonRpcRouteServices[type] = commonRpcRouteService;
	}
	
	public static ICommonRpcRouteService getCommonRpcRouteService(){
		return commonRpcRouteServices[0];
	}
}
