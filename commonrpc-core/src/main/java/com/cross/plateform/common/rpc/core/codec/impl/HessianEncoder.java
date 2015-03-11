/**
 * 
 */
package com.cross.plateform.common.rpc.core.codec.impl;

import java.io.ByteArrayOutputStream;

import com.caucho.hessian.io.Hessian2Output;
import com.cross.plateform.common.rpc.core.codec.CommonRpcEncoder;
/**
 * @author liubing1
 *
 */
public class HessianEncoder implements CommonRpcEncoder {

	/* (non-Javadoc)
	 * @see com.jd.cross.plateform.rocketrpc.core.codec.RocketRPCEncoder#encode(java.lang.Object)
	 */
	@Override
	public byte[] encode(Object object) throws Exception {
		// TODO Auto-generated method stub
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		Hessian2Output output = new Hessian2Output(byteArray);
		output.writeObject(object);
		output.close();
		byte[] bytes = byteArray.toByteArray();
		return bytes;
	}

}
