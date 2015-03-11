/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.client.proxy;

import java.lang.reflect.Proxy;

import com.cross.plateform.common.rpc.client.proxy.ClientProxy;
import com.cross.plateform.common.rpc.core.util.SocketAddressUtil;
import com.cross.plateform.common.rpc.tcp.netty4.client.invocation.CommonRpcTcpClientInvocationHandler;
/**
 * @author liubing1
 *
 */
public class CommonRpcTcpClientProxy implements ClientProxy{
	
	
	public CommonRpcTcpClientProxy() {

	}

	private static class SingletonHolder {
		static final CommonRpcTcpClientProxy instance = new CommonRpcTcpClientProxy();
	}

	public static CommonRpcTcpClientProxy getInstance() {
		return SingletonHolder.instance;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getProxyService(Class<T> clazz, int timeout, int codecType,
			int protocolType, String targetInstanceName, String address,String token) {
		// TODO Auto-generated method stub
		return (T) Proxy.newProxyInstance(
				Thread.currentThread().getContextClassLoader(),
				new Class[] { clazz },
				new CommonRpcTcpClientInvocationHandler(SocketAddressUtil
						.getInetSocketAddress(address), timeout,
						targetInstanceName, codecType, protocolType,token));
	}
}
