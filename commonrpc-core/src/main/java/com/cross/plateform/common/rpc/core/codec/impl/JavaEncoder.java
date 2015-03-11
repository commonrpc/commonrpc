/**
 * 
 */
package com.cross.plateform.common.rpc.core.codec.impl;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import com.cross.plateform.common.rpc.core.codec.CommonRpcEncoder;
/**
 * @author liubing1
 * jdk 反序列化
 */
public class JavaEncoder implements CommonRpcEncoder {

	/* (non-Javadoc)
	 * @see com.jd.cross.plateform.rocketrpc.core.codec.RocketRPCEncoder#encode(java.lang.Object)
	 */
	@Override
	public byte[] encode(Object object) throws Exception {
		// TODO Auto-generated method stub
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		ObjectOutputStream output = new ObjectOutputStream(byteArray);
		output.writeObject(object);
		output.flush();
		output.close();
		return byteArray.toByteArray(); 
	}

}
