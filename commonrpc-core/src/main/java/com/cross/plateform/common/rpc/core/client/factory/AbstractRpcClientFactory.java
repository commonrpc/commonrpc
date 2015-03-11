/**
 * 
 */
package com.cross.plateform.common.rpc.core.client.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cross.plateform.common.rpc.core.all.message.CommonRpcResponse;
import com.cross.plateform.common.rpc.core.client.AbstractRpcClient;
import com.cross.plateform.common.rpc.core.client.RpcClient;
/**
 * @author liubing1
 *
 */
public abstract class AbstractRpcClientFactory implements RpcClientFactory {
		
	protected static Map<Integer, CommonRpcResponse> responses = 
			new ConcurrentHashMap<Integer, CommonRpcResponse>();
	
	protected static Map<String, AbstractRpcClient> rpcClients = 
			new ConcurrentHashMap<String, AbstractRpcClient>();
	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.client.factory.RpcClientFactory#getClient(java.lang.String, int, int, boolean)
	 */
	@Override
	public RpcClient getClient(String host, int port) throws Exception {
		// TODO Auto-generated method stub
		String key="/"+host+":"+port;
		if(rpcClients.containsKey(key)){
			return rpcClients.get(key);
		}
		return createClient(host, port);
	}

	protected abstract RpcClient createClient(String targetIP, int targetPort) throws Exception;
	
	/**
	 * 停止客户端
	 */
	public abstract void stopClient() throws Exception;
	
	@Override
	public void receiveResponse(CommonRpcResponse response) throws Exception {
		// TODO Auto-generated method stub
		responses.put(response.getRequestId(), response);
	}
	
	@Override
	public CommonRpcResponse getResponse(Integer key) throws Exception {
		// TODO Auto-generated method stub
		return (CommonRpcResponse) responses.get(key);
	}
	
	@Override
	public void removeResponse(Integer key) {
		// TODO Auto-generated method stub
		responses.remove(key);
	}
	
	public void clearClients(){
		rpcClients.clear();
	}
	
	@Override
	public void putRpcClient(String key, AbstractRpcClient rpcClient) {
		// TODO Auto-generated method stub
		rpcClients.put(key, rpcClient);
	}
	
	@Override
	public boolean containClient(String key){
		return rpcClients.containsKey(key);
	}
	
	@Override
	public void removeRpcClient(String key) {
		// TODO Auto-generated method stub
		if(rpcClients.containsKey(key)){
			rpcClients.remove(key);
		}
	}
}
