/**
 * 
 */
package com.cross.plateform.common.rpc.core.server.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.cross.plateform.common.rpc.core.server.service.ICommonRpcRouteService;
import com.cross.plateform.common.rpc.core.server.service.bean.RpcRouteInfo;
import com.cross.plateform.common.rpc.core.util.ClassPoolUtils;
import com.cross.plateform.common.rpc.core.util.StringUtils;
import com.cross.plateform.common.rpc.server.filter.RpcFilter;
/**
 * @author liubing1
 *
 */
public class CommonRpcRouteServiceImpl implements ICommonRpcRouteService {
	
	public static final int TYPE = 0;
	
	private static List<RpcRouteInfo> rpcRouteInfos=new ArrayList<RpcRouteInfo>();
	
	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.server.service.ICommonRpcRouteService#isRouteInfos(java.lang.String, java.lang.String)
	 */
	@Override
	public RpcRouteInfo isRouteInfos(String route, String methodType,Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		RpcRouteInfo rpcRoute=null;
		for(RpcRouteInfo rpcRouteInfo:rpcRouteInfos){
			if(rpcRouteInfo.getRoute().equals(route)&&rpcRouteInfo.getHttpType().equals(methodType)){
				rpcRouteInfo.setParams(params);
				rpcRoute=rpcRouteInfo;
				break;
			}
		}
		return rpcRoute;
	}

	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.server.service.ICommonRpcRouteService#methodInvoke(com.cross.plateform.common.rpc.core.server.service.bean.RpcRouteInfo)
	 */
	@Override
	public Object methodInvoke(RpcRouteInfo routeInfo) throws Exception {
		// TODO Auto-generated method stub
		Method[] mds = routeInfo.getMethods();
		for(Method method:mds){
			if (routeInfo.getMethod().equals(method.getName())) {
				Class<?>[] mdTypes = method.getParameterTypes();
				
				Object[] objs = new Object[mdTypes.length];
				String[] paramNames=ClassPoolUtils.getMethodVariableName(routeInfo.getObjCls().getName(), routeInfo.getMethod());
				for (int j = 0; j < mdTypes.length; j++) {
					
					if (mdTypes[j].isAssignableFrom(Map.class)) {//hashmap 
						objs[j] = routeInfo.getParams();
					}else if (mdTypes[j].isArray()) {//数组
						Map<String, Object> params = routeInfo.getParams();
						Object[] object = new Object[params.keySet()
								.size()];
						int k = 0;
						for (String key : params.keySet()) {
							object[k] = params.get(key);
							k++;
						}
						objs[j] = object;
					} else if (Collection.class.isAssignableFrom(mdTypes[j])) {//list 类型
						Map<String, Object> params = routeInfo
								.getParams();
						List<Object> result = new LinkedList<Object>();
						for (String key : params.keySet()) {
							result.add(params.get(key));
						}
						objs[j] = result;
					}else if (mdTypes[j].isPrimitive()
							|| mdTypes[j].getName().startsWith("java.")) {// java 私有类型
						Map<String, Object> params = routeInfo.getParams();
						
						if(!StringUtils.isNullOrEmpty(paramNames)&&paramNames[j]!=null){
							if(params.containsKey(paramNames[j])){
								objs[j] = params.get(paramNames[j]);
							}
						}
						
					} else {// javabean
						objs[j] = mdTypes[j].newInstance();
						String json = JSONObject.toJSONString(routeInfo.getParams());
						objs[j] = JSONObject.parseObject(json, objs[j].getClass());
					}
				}//参数循环结束
				
				method.setAccessible(true);
				
				Object result=null;
				
				Object object=routeInfo.getObjCls().newInstance();
				
				if(routeInfo.getRpcFilter()!=null){
					RpcFilter rpcFilter=routeInfo.getRpcFilter();
					
					if(rpcFilter.doBeforeRequest(method, object, objs)){
						result=getResult(method, object, objs);
					}else{
						throw new Exception("无效的请求，服务端已经拒绝回应");
					}
					rpcFilter.doAfterRequest(result);
					
				}else{
					
					result=getResult(method, object, objs);
					
				}
				return result;
			}
		}
		return null;
	}
	
	private Object getResult(Method method,Object object,Object... args) throws Exception{
//		if (args.length > 0) {
//			return method.invoke(object, args);// 执行
//		} else {
//			return method.invoke(object, null);
//		}
		return method.invoke(object, args);// 执行
	}
	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.server.service.ICommonRpcRouteService#registerProcessor(java.lang.String, java.lang.Object, com.cross.plateform.common.rpc.server.filter.RpcFilter)
	 */
	@Override
	public void registerProcessor(String projectname, Object instance,RpcFilter rpcFilter,String httpType,String returnType) {
		// TODO Auto-generated method stub
		Method[] methods = instance.getClass().getDeclaredMethods();//自定义方法
		for(Method method:methods){
			RpcRouteInfo rpcRouteInfo=new RpcRouteInfo();
			rpcRouteInfo.setObjCls(instance.getClass());
			String simplename=instance.getClass().getSimpleName();
			simplename= simplename.substring(0, 1).toLowerCase()+ simplename.substring(1);
			
			rpcRouteInfo.setHttpType(httpType);
			rpcRouteInfo.setReturnType(returnType);
			rpcRouteInfo.setMethods(methods);
			rpcRouteInfo.setMethod(method.getName());
			rpcRouteInfo.setReturnType(returnType);
			rpcRouteInfo.setRpcFilter(rpcFilter);
			String route=projectname+"/"+simplename+"/"+rpcRouteInfo.getMethod();
			rpcRouteInfo.setRoute(route);
			
			rpcRouteInfos.add(rpcRouteInfo);
		}
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		rpcRouteInfos.clear();
	}

}
