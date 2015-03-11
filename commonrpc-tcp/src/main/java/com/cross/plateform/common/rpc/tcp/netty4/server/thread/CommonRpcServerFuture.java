/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.server.thread;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cross.plateform.common.rpc.core.all.message.CommonRpcResponse;
import com.cross.plateform.common.rpc.core.thread.AbstractCommonRpcFuture;

/**
 * @author liubing1
 *
 */
public class CommonRpcServerFuture extends AbstractCommonRpcFuture<CommonRpcResponse> {
	
	private static final Log LOGGER = LogFactory
			.getLog(CommonRpcServerFuture.class);
	
	private ChannelHandlerContext ctx;
	
	public CommonRpcServerFuture(ChannelHandlerContext ctx) {
		super();
		this.ctx = ctx;
	}

	@Override
	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		LOGGER.error("server handler fail!", arg0);
	}

	@Override
	public void onSuccess(CommonRpcResponse arg0) {
		// TODO Auto-generated method stub
		if(ctx.channel().isOpen()&&arg0!=null){
			ChannelFuture wf = ctx.channel().writeAndFlush(arg0);
		    wf.addListener(new ChannelFutureListener() {
		      public void operationComplete(ChannelFuture future) throws Exception {
		        if (!future.isSuccess()) {
		          LOGGER.error("server write response error,client  host is: " + ((InetSocketAddress) ctx.channel().remoteAddress()).getHostName()+":"+((InetSocketAddress) ctx.channel().remoteAddress()).getPort()+",server Ip:"+getLocalhost());
		          ctx.channel().close();
		        }
		      }
		    });
		}
		
	}
	
	private String getLocalhost(){
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			return ip;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("无法获取本地Ip",e);
		}
		
	}
}
