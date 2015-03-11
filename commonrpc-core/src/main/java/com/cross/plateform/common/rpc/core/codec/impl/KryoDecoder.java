/**
 * 
 */
package com.cross.plateform.common.rpc.core.codec.impl;
import com.cross.plateform.common.rpc.core.codec.CommonRpcDecoder;
import com.cross.plateform.common.rpc.core.util.KryoUtils;
import com.esotericsoftware.kryo.io.Input;

/**
 * @author liubing1
 * Kryo 解码
 */
public class KryoDecoder implements CommonRpcDecoder {

	/* (non-Javadoc)
	 * @see com.jd.cross.plateform.rocketrpc.core.codec.RocketRPCDecoder#decode(java.lang.String, byte[])
	 */
	@Override
	public Object decode(String className, byte[] bytes) throws Exception {
		// TODO Auto-generated method stub
		Input input = new Input(bytes);
		return KryoUtils.getKryo().readClassAndObject(input);
	}

}
