/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.client.handler;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cross.plateform.common.rpc.core.all.message.CommonRpcResponse;
import com.cross.plateform.common.rpc.tcp.netty4.client.factory.CommonRpcTcpClientFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author liubing1
 * 
 */
public class CommonRpcTcpClientHandler extends ChannelInboundHandlerAdapter {

	private static final Log LOGGER = LogFactory
			.getLog(CommonRpcTcpClientHandler.class);

	private static final boolean isDebugEnabled = LOGGER.isDebugEnabled();
		
	public CommonRpcTcpClientHandler() {
		super();
		
	}
	
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (msg instanceof CommonRpcResponse) {
			CommonRpcResponse response = (CommonRpcResponse) msg;
			if (isDebugEnabled) {
				// for performance trace
				LOGGER.debug("receive response list from server: "
						+ ctx.channel().remoteAddress() + ",request is:"
						+ response.getRequestId());
			}
			
			CommonRpcTcpClientFactory.getInstance().receiveResponse(response);
		} else {
			LOGGER.error("receive message error,only support List || ResponseWrapper");
			throw new Exception(
					"receive message error,only support List || ResponseWrapper");
		}
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//LOGGER.info(CommonRpcTcpClientFactory.getInstance().containClient(ctx.channel().remoteAddress().toString()));
		super.channelReadComplete(ctx);
	}
	
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable e)
			throws Exception {
		if (!(e.getCause() instanceof IOException)) {
			// only log
			LOGGER.error("catch some exception not IOException", e);
		}
		CommonRpcTcpClientFactory.getInstance().removeRpcClient(ctx.channel().remoteAddress().toString());
		if(ctx.channel().isOpen()){
			ctx.channel().close();
		}
		
		
	}

	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		LOGGER.error("connection closed: " + ctx.channel().remoteAddress());
		CommonRpcTcpClientFactory.getInstance().removeRpcClient(ctx.channel().remoteAddress().toString());
		if(ctx.channel().isOpen()){
			ctx.channel().close();
		}
	}
	
	
}
