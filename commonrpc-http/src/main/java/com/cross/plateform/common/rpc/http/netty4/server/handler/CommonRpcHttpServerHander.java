/**
 * 
 */
package com.cross.plateform.common.rpc.http.netty4.server.handler;
import java.util.concurrent.TimeUnit;

import com.cross.plateform.common.rpc.core.thread.CommonRpcTaskExecutors;
import com.cross.plateform.common.rpc.http.netty4.server.bean.ServerBean;
import com.cross.plateform.common.rpc.http.netty4.server.thread.CommonRpcHttpServerTask;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * @author liubing1
 *
 */
public class CommonRpcHttpServerHander extends ChannelInboundHandlerAdapter {
	
	private int timeout;
	
	
	public CommonRpcHttpServerHander(int timeout) {
		super();
		this.timeout = timeout;
	}
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// TODO Auto-generated method stub
		ListeningExecutorService service = CommonRpcTaskExecutors.getInstance()
				.getService();
		ListenableFuture<ServerBean> future=service.submit(new CommonRpcHttpServerTask(msg));
		try{
			ServerBean result=future.get(timeout,TimeUnit.MILLISECONDS);
			if(result!=null){
				if(ctx.channel().isOpen()&&result!=null){
					 if(result.getDefaultHttpContent()!=null){
						 ctx.write(result.getResponse());
						 ctx.writeAndFlush(result.getDefaultHttpContent());
						 
					 }else{
						 ctx.writeAndFlush(result.getResponse());
					 } 	
				}
			}
		}catch(Exception e){
			DefaultHttpResponse httpResponse=new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR);
			httpResponse.headers().add(HttpHeaders.Names.TRANSFER_ENCODING,
					HttpHeaders.Values.CHUNKED);
			
			httpResponse.headers().set(HttpHeaders.Names.CONTENT_TYPE,
					"text/plain; charset=UTF-8");
			
			httpResponse.headers().set(HttpHeaders.Names.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
			httpResponse.headers().set(HttpHeaders.Names.CACHE_CONTROL,"no-cache");
			httpResponse.headers().set(HttpHeaders.Names.PRAGMA,"no-cache");
			httpResponse.headers().set(HttpHeaders.Names.EXPIRES,"-1");
			DefaultHttpContent defaultHttpContent = new DefaultHttpContent(Unpooled.copiedBuffer(e.getMessage(), CharsetUtil.UTF_8));
			ServerBean result =new ServerBean(httpResponse, defaultHttpContent);
			ctx.write(result.getResponse());
			ctx.writeAndFlush(result.getDefaultHttpContent());
		}finally{
			DefaultLastHttpContent lastHttpContent = new DefaultLastHttpContent();
			ctx.writeAndFlush(lastHttpContent);
		}
		
		
	}
	
}
