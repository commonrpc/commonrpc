/**
 * 
 */
package com.cross.plateform.common.rpc.core.server.handler.factory;

import com.cross.plateform.common.rpc.core.server.handler.AbstractRpcHttpServerHandler;
import com.cross.plateform.common.rpc.core.server.handler.AbstractRpcTcpServerHandler;
import com.cross.plateform.common.rpc.core.server.handler.impl.RpcHttpServerHandlerImpl;
import com.cross.plateform.common.rpc.core.server.handler.impl.RpcTcpServerHandlerImpl;

/**
 * @author liubing1
 *
 */
public class CommonRpcServerHandlerFactory {
	
	private static AbstractRpcTcpServerHandler[] serverHandlers = new AbstractRpcTcpServerHandler[1];
	
	private static AbstractRpcHttpServerHandler[] httpserverHandlers = new AbstractRpcHttpServerHandler[2];
	static{
		registerProtocol(RpcTcpServerHandlerImpl.TYPE, new RpcTcpServerHandlerImpl(),RpcHttpServerHandlerImpl.TYPE,new RpcHttpServerHandlerImpl());
	}
	
	private static void registerProtocol(int type,AbstractRpcTcpServerHandler customServerHandler,int httptype,AbstractRpcHttpServerHandler httpServerHandler){
		if(type > serverHandlers.length){
			AbstractRpcTcpServerHandler[] newServerHandlers = new AbstractRpcTcpServerHandler[type + 1];
			System.arraycopy(serverHandlers, 0, newServerHandlers, 0, serverHandlers.length);
			serverHandlers = newServerHandlers;
		}
		serverHandlers[type] = customServerHandler;
		
		if(httptype > httpserverHandlers.length){
			AbstractRpcHttpServerHandler[] newServerHandlers = new AbstractRpcHttpServerHandler[type + 1];
			System.arraycopy(serverHandlers, 0, newServerHandlers, 0, serverHandlers.length);
			httpserverHandlers = newServerHandlers;
		}
		httpserverHandlers[type] = httpServerHandler;
	}
	
	public static AbstractRpcTcpServerHandler getServerHandler(){
		return serverHandlers[0];
	}
	
	public static AbstractRpcHttpServerHandler getHttpServerHandler(){
		return httpserverHandlers[0];
	}
}
