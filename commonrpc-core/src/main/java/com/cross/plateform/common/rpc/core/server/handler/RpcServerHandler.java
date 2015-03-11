/**
 * 
 */
package com.cross.plateform.common.rpc.core.server.handler;

import com.cross.plateform.common.rpc.server.filter.RpcFilter;

/**
 * @author liubing1
 *
 */
public interface RpcServerHandler {
	
	/**
	 * 注册服务
	 * @param instanceName
	 * @param instance
	 */
	public void registerProcessor(String instanceName, Object instance,RpcFilter rpcFilter);

	/**
	 * 清除
	 */
	public void clear();
}
