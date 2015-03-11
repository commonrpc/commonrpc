/**
 * 
 */
package com.cross.plateform.common.rpc.core.codec.all;

import com.cross.plateform.common.rpc.core.codec.CommonRpcDecoder;
import com.cross.plateform.common.rpc.core.codec.CommonRpcEncoder;
import com.cross.plateform.common.rpc.core.codec.impl.HessianDecoder;
import com.cross.plateform.common.rpc.core.codec.impl.HessianEncoder;
import com.cross.plateform.common.rpc.core.codec.impl.JavaDecoder;
import com.cross.plateform.common.rpc.core.codec.impl.JavaEncoder;
import com.cross.plateform.common.rpc.core.codec.impl.KryoDecoder;
import com.cross.plateform.common.rpc.core.codec.impl.KryoEncoder;
import com.cross.plateform.common.rpc.core.codec.impl.ProtocolBufDecoder;
import com.cross.plateform.common.rpc.core.codec.impl.ProtocolBufEncoder;

/**
 * @author liubing1
 * 
 */
public class CommonRpcCodecs {

	public static final int JAVA_CODEC = 1;

	public static final int HESSIAN_CODEC = 2;

	public static final int PB_CODEC = 3;

	public static final int KRYO_CODEC = 4;

	private static CommonRpcEncoder[] encoders = new CommonRpcEncoder[5];

	private static CommonRpcDecoder[] decoders = new CommonRpcDecoder[5];

	static {
		addEncoder(JAVA_CODEC, new JavaEncoder());
		addEncoder(HESSIAN_CODEC, new HessianEncoder());
		addEncoder(PB_CODEC, new ProtocolBufEncoder());
		addEncoder(KRYO_CODEC, new KryoEncoder());
		addDecoder(JAVA_CODEC, new JavaDecoder());
		addDecoder(HESSIAN_CODEC, new HessianDecoder());
		addDecoder(PB_CODEC, new ProtocolBufDecoder());
		addDecoder(KRYO_CODEC, new KryoDecoder());
	}

	public static void addEncoder(int encoderKey, CommonRpcEncoder encoder) {
		if (encoderKey > encoders.length) {
			CommonRpcEncoder[] newEncoders = new CommonRpcEncoder[encoderKey + 1];
			System.arraycopy(encoders, 0, newEncoders, 0, encoders.length);
			encoders = newEncoders;
		}
		encoders[encoderKey] = encoder;
	}

	public static void addDecoder(int decoderKey, CommonRpcDecoder decoder) {
		if (decoderKey > decoders.length) {
			CommonRpcDecoder[] newDecoders = new CommonRpcDecoder[decoderKey + 1];
			System.arraycopy(decoders, 0, newDecoders, 0, decoders.length);
			decoders = newDecoders;
		}
		decoders[decoderKey] = decoder;
	}

	public static CommonRpcEncoder getEncoder(int encoderKey) {
		return encoders[encoderKey];
	}

	public static CommonRpcDecoder getDecoder(int decoderKey) {
		return decoders[decoderKey];
	}
}
