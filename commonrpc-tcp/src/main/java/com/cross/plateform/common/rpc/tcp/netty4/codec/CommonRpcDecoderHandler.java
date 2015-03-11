/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.codec;

import java.util.List;

import com.cross.plateform.common.rpc.core.protocol.all.CommonRpcProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author liubing1
 *
 */
public class CommonRpcDecoderHandler extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf,
			List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		CommonRpcByteBuffer wrapper = new CommonRpcByteBuffer(buf);
		    Object result = CommonRpcProtocol.decode(wrapper, null);
		    if (result != null) {
		      out.add(result);
		    }
	}

}
