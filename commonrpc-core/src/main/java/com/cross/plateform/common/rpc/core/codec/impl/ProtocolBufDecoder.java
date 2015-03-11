/**
 * 
 */
package com.cross.plateform.common.rpc.core.codec.impl;

import java.util.concurrent.ConcurrentHashMap;
import com.cross.plateform.common.rpc.core.codec.CommonRpcDecoder;
import com.google.protobuf.Message;
/**
 * @author liubing1
 *
 */
public class ProtocolBufDecoder implements CommonRpcDecoder {
	
	private static ConcurrentHashMap<String, Message> messages = new ConcurrentHashMap<String, Message>();

	public static void addMessage(String className,Message message){
		messages.putIfAbsent(className, message);
	}
	/* (non-Javadoc)
	 * @see com.jd.cross.plateform.rocketrpc.core.codec.RocketRPCDecoder#decode(java.lang.String, byte[])
	 */
	@Override
	public Object decode(String className, byte[] bytes) throws Exception {
		// TODO Auto-generated method stub
		Message message = messages.get(className);
		return message.newBuilderForType().mergeFrom(bytes).build();
	}

}
