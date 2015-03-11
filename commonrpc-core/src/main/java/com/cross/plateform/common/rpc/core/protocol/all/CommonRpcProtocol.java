package com.cross.plateform.common.rpc.core.protocol.all;

import com.cross.plateform.common.rpc.core.all.message.CommonRpcRequest;
import com.cross.plateform.common.rpc.core.all.message.CommonRpcResponse;
import com.cross.plateform.common.rpc.core.bytebuffer.RpcByteBuffer;
import com.cross.plateform.common.rpc.core.protocol.RpcProtocol;
import com.cross.plateform.common.rpc.core.protocol.factory.CommonRpcProtocolFactory;


/**
 * 
 * @author liubing1
 *
 */
public class CommonRpcProtocol {

	public static final int HEADER_LEN = 2;
	
	public static final byte CURRENT_VERSION = (byte)1;
	
	public static RpcByteBuffer encode(Object message,RpcByteBuffer bytebufferWrapper) throws Exception {
		Integer type = 0;
		if(message instanceof CommonRpcRequest){
			type = ((CommonRpcRequest)message).getProtocolType();
		}else if(message instanceof CommonRpcResponse){
			type = ((CommonRpcResponse)message).getProtocolType();
		}
		return CommonRpcProtocolFactory.getProtocol(type).encode(message, bytebufferWrapper);
	}

	public static Object decode(RpcByteBuffer wrapper, Object errorObject) throws Exception {
		final int originPos = wrapper.readerIndex();
		if(wrapper.readableBytes() < 2){
			wrapper.setReaderIndex(originPos);
        	return errorObject;
        }
		int version = wrapper.readByte();
		if(version == 1){
			int type = wrapper.readByte();
			RpcProtocol protocol = CommonRpcProtocolFactory.getProtocol(type);
			if(protocol == null){
				throw new Exception("Unsupport protocol type: "+type);
			}
			return CommonRpcProtocolFactory.getProtocol(type).decode(wrapper, errorObject, new int[]{originPos});
		}else{
			throw new Exception("Unsupport protocol version: "+ version);
		}
	}

}
