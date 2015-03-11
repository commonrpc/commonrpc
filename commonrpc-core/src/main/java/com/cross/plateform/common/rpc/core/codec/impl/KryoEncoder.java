/**
 * 
 */
package com.cross.plateform.common.rpc.core.codec.impl;

import com.cross.plateform.common.rpc.core.codec.CommonRpcEncoder;
import com.cross.plateform.common.rpc.core.util.KryoUtils;
import com.esotericsoftware.kryo.io.Output;
/**
 * @author liubing1
 *
 */
public class KryoEncoder implements CommonRpcEncoder {

	/* (non-Javadoc)
	 * @see com.jd.cross.plateform.rocketrpc.core.codec.RocketRPCEncoder#encode(java.lang.Object)
	 */
	@Override
	public byte[] encode(Object object) throws Exception {
		// TODO Auto-generated method stub
		Output output = new Output(256);
		KryoUtils.getKryo().writeClassAndObject(output, object);
		return output.toBytes();
	}

}
