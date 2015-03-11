/**
 * 
 */
package com.cross.plateform.common.rpc.core.protocol.factory;

import com.cross.plateform.common.rpc.core.protocol.RpcProtocol;
import com.cross.plateform.common.rpc.core.protocol.impl.DefualtRpcProtocolImpl;

/**
 * @author liubing1
 *
 */
public class CommonRpcProtocolFactory {
	
	private static RpcProtocol[] protocolHandlers = new RpcProtocol[2];
	
	
	static{
		registerProtocol(DefualtRpcProtocolImpl.TYPE, new DefualtRpcProtocolImpl());
	}
	
	private static void registerProtocol(int type,RpcProtocol customProtocol){
		if(type > protocolHandlers.length){
			RpcProtocol[] newProtocolHandlers = new RpcProtocol[type + 1];
			System.arraycopy(protocolHandlers, 0, newProtocolHandlers, 0, protocolHandlers.length);
			protocolHandlers = newProtocolHandlers;
			
		}
		protocolHandlers[type] = customProtocol;
	}
	
	public static RpcProtocol getProtocol(int type){
		return protocolHandlers[type];
	}

}
