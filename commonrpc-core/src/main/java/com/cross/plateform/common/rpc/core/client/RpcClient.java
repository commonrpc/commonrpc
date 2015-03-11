/**
 * 
 */
package com.cross.plateform.common.rpc.core.client;
import com.cross.plateform.common.rpc.core.client.factory.RpcClientFactory;
/**
 * @author liubing1
 *
 */
public interface RpcClient {
	
	
	/**
	 * 动态调用
	 * @param targetInstanceName
	 * @param methodName
	 * @param argTypes
	 * @param args
	 * @param timeout
	 * @param codecType
	 * @param protocolType
	 * @return
	 * @throws Exception
	 */
	public Object invokeImpl(String targetInstanceName, String methodName,
			String[] argTypes, Object[] args, int timeout, int codecType, int protocolType,String token)
			throws Exception;
	
	
	
	
	
	/**
	 * server address
	 * 
	 * @return String
	 */
	public String getServerIP();

	/**
	 * server port
	 * 
	 * @return int
	 */
	public int getServerPort();
	
	/**
	 * Get factory
	 */
	public RpcClientFactory getClientFactory();
	
	
}
