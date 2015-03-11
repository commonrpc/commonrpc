/**
 * 
 */
package com.cross.plateform.common.rpc.client.proxy;

/**
 * @author liubing1
 *
 */
public interface ClientProxy {
	
	public <T> T getProxyService(Class<T> clazz, int timeout, int codecType,
			int protocolType, String targetInstanceName, String address,String token);
}
