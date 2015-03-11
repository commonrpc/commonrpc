/**
 * 
 */
package com.cross.plateform.common.rpc.core.server.handler.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cross.plateform.common.rpc.core.all.message.CommonRpcRequest;
import com.cross.plateform.common.rpc.core.all.message.CommonRpcResponse;
import com.cross.plateform.common.rpc.core.codec.all.CommonRpcCodecs;
import com.cross.plateform.common.rpc.core.server.handler.AbstractRpcTcpServerHandler;
import com.cross.plateform.common.rpc.server.filter.RpcFilter;

/**
 * @author liubing1
 * 服务端处理请求类
 */
public class RpcTcpServerHandlerImpl extends AbstractRpcTcpServerHandler {
	
	public static final int TYPE = 0;
	
	private static final Log LOGGER = LogFactory.getLog(RpcTcpServerHandlerImpl.class);
	
	private static Map<String, RpcFilterServerBean> processors = new HashMap<String, RpcFilterServerBean>();
	
	private static Map<String, Method> cacheMethods = new HashMap<String, Method>();

	/* (non-Javadoc)
	 * @see com.jd.cross.plateform.rocketrpc.core.server.handler.RocketRPCServerHandler#registerProcessor(java.lang.String, java.lang.Object)
	 */
	@Override
	public void registerProcessor(String instanceName, Object instance,RpcFilter rpcFilter) {
		// TODO Auto-generated method stub
		RpcFilterServerBean filterServerBean=new RpcFilterServerBean(instance, rpcFilter);
		
		processors.put(instanceName, filterServerBean);
		Class<?> instanceClass = instance.getClass();
		Method[] methods = instanceClass.getDeclaredMethods();
		for (Method method : methods) {
			Class<?>[] argTypes = method.getParameterTypes();
			StringBuilder methodKeyBuilder = new StringBuilder();
			methodKeyBuilder.append(instanceName).append("#");
			methodKeyBuilder.append(method.getName()).append("$");
			for (Class<?> argClass : argTypes) {
				methodKeyBuilder.append(argClass.getName()).append("_");
			}
			cacheMethods.put(methodKeyBuilder.toString(), method);
		}
	}

	/* (non-Javadoc)
	 * @see com.jd.cross.plateform.rocketrpc.core.server.handler.RocketRPCServerHandler#handleRequest(com.jd.cross.plateform.rocketrpc.core.wrapper.RocketRPCRequest)
	 */
	@Override
	public CommonRpcResponse handleRequest(CommonRpcRequest request,int codecType,int procotolType ) {
		// TODO Auto-generated method stub
		CommonRpcResponse responseWrapper = new CommonRpcResponse(request.getId(),codecType,procotolType);
		String targetInstanceName = new String(request.getTargetInstanceName());
		String methodName = new String(request.getMethodName());
		byte[][] argTypeBytes  = request.getArgTypes();
		String[] argTypes = new String[argTypeBytes.length];
		for(int i = 0; i <argTypeBytes.length; i++) {
		    argTypes[i] = new String(argTypeBytes[i]);
		}
		Object[] requestObjects = null;
		Method method = null;
		try{
			RpcFilterServerBean rpcFilterServerBean = processors.get(targetInstanceName);
			if(rpcFilterServerBean == null){
				throw new Exception("no "+targetInstanceName+" instance exists on the server");
			}
			if (argTypes != null && argTypes.length > 0) {
				StringBuilder methodKeyBuilder = new StringBuilder();
				methodKeyBuilder.append(targetInstanceName).append("#");
				methodKeyBuilder.append(methodName).append("$");
				Class<?>[] argTypeClasses = new Class<?>[argTypes.length];
				for (int i = 0; i < argTypes.length; i++) {
					methodKeyBuilder.append(argTypes[i]).append("_");
					argTypeClasses[i] = Class.forName(argTypes[i]);
				}
				requestObjects = new Object[argTypes.length];
				method = cacheMethods.get(methodKeyBuilder.toString());
				if(method == null){
					throw new Exception("no method: "+methodKeyBuilder.toString()+" find in "+targetInstanceName+" on the server");
				}
				Object[] tmprequestObjects = request
						.getRequestObjects();
				for (int i = 0; i < tmprequestObjects.length; i++) {
					try{
						requestObjects[i] = CommonRpcCodecs.getDecoder(request.getCodecType()).decode(argTypes[i],(byte[])tmprequestObjects[i]);
					}
					catch(Exception e){
						throw new Exception("decode request object args error",e);
					}
				}
			} else {
				method = rpcFilterServerBean.getObject().getClass().getMethod(methodName,
						new Class<?>[] {});
				if(method == null){
					throw new Exception("no method: "+methodName+" find in "+targetInstanceName+" on the server");
				}
				requestObjects = new Object[] {};
			}
			method.setAccessible(true);
			if(rpcFilterServerBean.getRpcFilter()!=null){
				RpcFilter rpcFilter=rpcFilterServerBean.getRpcFilter();
				if(rpcFilter.doBeforeRequest(method, rpcFilterServerBean.getObject(), requestObjects)){
					responseWrapper.setResponse(method.invoke(rpcFilterServerBean.getObject(), requestObjects));
				}else{
					responseWrapper.setException(new Exception("无效的请求，服务端已经拒绝回应"));
				}
				rpcFilter.doAfterRequest(responseWrapper.getResponse());
			}else{
				responseWrapper.setResponse(method.invoke(rpcFilterServerBean.getObject(), requestObjects));
			}
			
		}catch(Exception e){
			LOGGER.error("server handle request error",e);
			responseWrapper.setException(e);
		}
		return responseWrapper;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		processors.clear();
		cacheMethods.clear();
	}
	
	
	class RpcFilterServerBean {
		
		private Object object;
		
		private RpcFilter rpcFilter;

		/**
		 * @return the object
		 */
		public Object getObject() {
			return object;
		}

		/**
		 * @param object the object to set
		 */
		public void setObject(Object object) {
			this.object = object;
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

		public RpcFilterServerBean(Object object, RpcFilter rpcFilter) {
			super();
			this.object = object;
			this.rpcFilter = rpcFilter;
		}
		
		
	}
}
