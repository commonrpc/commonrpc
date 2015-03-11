/**
 * 
 */
package com.cross.plateform.common.rpc.core.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cross.plateform.common.rpc.core.all.message.CommonRpcRequest;
import com.cross.plateform.common.rpc.core.all.message.CommonRpcResponse;
import com.cross.plateform.common.rpc.core.codec.all.CommonRpcCodecs;
import com.cross.plateform.common.rpc.core.util.StringUtils;


/**
 * @author liubing1
 *
 */
public abstract class AbstractRpcClient implements RpcClient {
	
	private static final Log LOGGER = LogFactory.getLog(AbstractRpcClient.class);

	
	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.client.RPCClient#invokeImpl(java.lang.String, java.lang.String, java.lang.String[], java.lang.Object[], int, int, int)
	 */
	@Override
	public Object invokeImpl(String targetInstanceName, String methodName,
			String[] argTypes, Object[] args, int timeout, int codecType,
			int protocolType,String token) throws Exception {
		// TODO Auto-generated method stub
		byte[][] argTypeBytes = new byte[argTypes.length][];
		for(int i =0; i < argTypes.length; i++) {
		    argTypeBytes[i] =  argTypes[i].getBytes();
		}
		
		CommonRpcRequest wrapper = new CommonRpcRequest(targetInstanceName.getBytes(),
				methodName.getBytes(), argTypeBytes, args, timeout, codecType, protocolType,token.getBytes());
		
		return invokeImplIntern(wrapper);
	}
	
	private Object invokeImplIntern(CommonRpcRequest rocketRPCRequest) throws Exception {
		
		CommonRpcResponse commonRPCResponse = null;

		try {
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("client ready to send message,request id: "+rocketRPCRequest.getId());
			}
			sendRequest(rocketRPCRequest);
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("client write message to send buffer,wait for response,request id: "+rocketRPCRequest.getId());
			}
		}catch (Exception e) {
			commonRPCResponse = null;
			LOGGER.error("send request to os sendbuffer error", e);
			throw new RuntimeException("send request to os sendbuffer error", e);
		}
		
		Object result = null;
		try {
			long begintime=System.currentTimeMillis();
			boolean flag=true;
			while (true) {
				result =getClientFactory().getResponse(rocketRPCRequest.getId());
				if(result!=null){
					break;
				}else{
					long endtime=System.currentTimeMillis();
					if(endtime-begintime>rocketRPCRequest.getTimeout()){//timeout
						LOGGER.error("request timeout");
						flag=false;
						break;
					}
				}
			}
			
			if(!flag){
				result=null;
			}
			
		}catch(Exception e){
			//responses.remove(rocketRPCRequest.getId());
			LOGGER.error("receive response timeout ", e);
		}finally{
			getClientFactory().removeResponse(rocketRPCRequest.getId());
		}

		if (result == null) {
			String errorMsg = "receive response timeout("
					+ rocketRPCRequest.getTimeout() + " ms),server is: "
					+ getServerIP() + ":" + getServerPort()
					+ " request id is:" + rocketRPCRequest.getId();
			throw new Exception(errorMsg);
		}
		
		if(result instanceof CommonRpcResponse){
			commonRPCResponse = (CommonRpcResponse) result;
			
		}else{
			throw new Exception("only receive ResponseWrapper or List as response");
		}
		
		try{
			if (commonRPCResponse.getResponse() instanceof byte[]) {
				String responseClassName = null;
				if(commonRPCResponse.getResponseClassName() != null){
					responseClassName = new String(commonRPCResponse.getResponseClassName());
				}
				if(((byte[])commonRPCResponse.getResponse()).length == 0){
					commonRPCResponse.setResponse(null);
				}else{
					Object responseObject = CommonRpcCodecs.getDecoder(commonRPCResponse.getCodecType()).decode(
						responseClassName,(byte[]) commonRPCResponse.getResponse());
					if (responseObject instanceof Throwable) {
						commonRPCResponse.setException((Throwable) responseObject);
					} 
					else {
						commonRPCResponse.setResponse(responseObject);
					}
				}
			}
		}catch(Exception e){
			LOGGER.error("Deserialize response object error", e);
			throw new Exception("Deserialize response object error", e);
		}
		
		if (!StringUtils.isNullOrEmpty(commonRPCResponse.getException())) {
			Throwable t = commonRPCResponse.getException();
			t.fillInStackTrace();
			String errorMsg = "server error,server is: " + getServerIP()
					+ ":" + getServerPort() + " request id is:"
					+ rocketRPCRequest.getId();
			LOGGER.error(errorMsg, t);
			throw new Exception(errorMsg, t);
		}
		
		return commonRPCResponse.getResponse();
	}
	/**
	 * 发送请求
	 * @param rocketRPCRequest
	 * @param timeout
	 * @throws Exception
	 */
	public abstract void sendRequest(CommonRpcRequest commonRpcRequest) throws Exception; 
}
