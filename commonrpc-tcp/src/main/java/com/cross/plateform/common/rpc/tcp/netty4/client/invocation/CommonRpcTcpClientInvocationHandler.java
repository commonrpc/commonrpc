/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.client.invocation;

import java.net.InetSocketAddress;
import java.util.List;

import com.cross.plateform.common.rpc.core.client.factory.RpcClientFactory;
import com.cross.plateform.common.rpc.core.client.invocation.AbstractRpcClientInvocationHandler;
import com.cross.plateform.common.rpc.tcp.netty4.client.factory.CommonRpcTcpClientFactory;

/**
 * @author liubing1
 *
 */
public class CommonRpcTcpClientInvocationHandler extends
		AbstractRpcClientInvocationHandler {

	public CommonRpcTcpClientInvocationHandler(List<InetSocketAddress> servers,
			int timeout,  String targetInstanceName,
			int codecType, int protocolType,String token) {
		super(servers, timeout, targetInstanceName, codecType,
				protocolType,token);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.client.invocation.AbstractRpcClientInvocationHandler#getClientFactory()
	 */
	@Override
	public RpcClientFactory getClientFactory() {
		// TODO Auto-generated method stub
		return CommonRpcTcpClientFactory.getInstance();
	}

}
