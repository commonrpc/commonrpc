/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.server.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cross.plateform.common.rpc.core.all.message.CommonRpcRequest;
import com.cross.plateform.common.rpc.core.all.message.CommonRpcResponse;
import com.cross.plateform.common.rpc.core.server.handler.factory.CommonRpcServerHandlerFactory;
import com.cross.plateform.common.rpc.core.thread.AbstractCommonRpcTask;
import com.cross.plateform.common.rpc.core.util.StringUtils;
/**
 * @author liubing1
 *
 */
public class RocketRPCServerTask extends AbstractCommonRpcTask<CommonRpcResponse> {
	
	private static final Log LOGGER = LogFactory
			.getLog(RocketRPCServerTask.class);
	
    private Object message;
	
    private String token;
    
    private int procotolType;//协议名称
	
	private int codecType;//编码类型

	public RocketRPCServerTask(Object message, String token, int procotolType,
			int codecType) {
		super();
		this.message = message;
		this.token = token;
		this.procotolType = procotolType;
		this.codecType = codecType;
	}


	@Override
	public CommonRpcResponse call() throws Exception {
		// TODO Auto-generated method stub
		CommonRpcResponse rocketRPCResponse =null;
		
		if(message instanceof CommonRpcRequest){
			CommonRpcRequest request = (CommonRpcRequest) message;
			if(!StringUtils.isNullOrEmpty(request.getToken())){
				String token=new String(request.getToken());
				if(!this.token.equals(token)){
					LOGGER.error("client token is wrong");
					rocketRPCResponse=new CommonRpcResponse(request.getId(),codecType,procotolType);
			          rocketRPCResponse.setException(new Exception("client token is wrong"));
			          return rocketRPCResponse;
				}
			}
			
	        rocketRPCResponse =
	        		CommonRpcServerHandlerFactory.getServerHandler().handleRequest(request,codecType,procotolType);
	        
		}
		return rocketRPCResponse;
	}


	/**
	 * @return the procotolType
	 */
	public int getProcotolType() {
		return procotolType;
	}


	/**
	 * @param procotolType the procotolType to set
	 */
	public void setProcotolType(int procotolType) {
		this.procotolType = procotolType;
	}


	/**
	 * @return the codecType
	 */
	public int getCodecType() {
		return codecType;
	}


	/**
	 * @param codecType the codecType to set
	 */
	public void setCodecType(int codecType) {
		this.codecType = codecType;
	}
	
	
	
}
