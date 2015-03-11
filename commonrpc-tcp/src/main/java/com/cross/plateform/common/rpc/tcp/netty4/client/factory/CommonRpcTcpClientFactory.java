/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.client.factory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cross.plateform.common.rpc.core.client.RpcClient;
import com.cross.plateform.common.rpc.core.client.factory.AbstractRpcClientFactory;
import com.cross.plateform.common.rpc.core.thread.NamedThreadFactory;
import com.cross.plateform.common.rpc.tcp.netty4.client.CommonRpcTcpClient;
import com.cross.plateform.common.rpc.tcp.netty4.client.handler.CommonRpcTcpClientHandler;
import com.cross.plateform.common.rpc.tcp.netty4.codec.CommonRpcDecoderHandler;
import com.cross.plateform.common.rpc.tcp.netty4.codec.CommonRpcEncoderHandler;
/**
 * @author liubing1
 *
 */
public class CommonRpcTcpClientFactory extends AbstractRpcClientFactory {
	
	private static final Log LOGGER = LogFactory.getLog(CommonRpcTcpClientFactory.class);
	
	private static AbstractRpcClientFactory _self = new CommonRpcTcpClientFactory();
	
	private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
	
	private static final ThreadFactory workerThreadFactory = new NamedThreadFactory("NETTYCLIENT-WORKER-");

	private static EventLoopGroup workerGroup = new NioEventLoopGroup(PROCESSORS, workerThreadFactory);
	
	private final Bootstrap bootstrap = new Bootstrap();
	
	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.client.factory.RpcClientFactory#startClient()
	 */
	@Override
	public void startClient(int connectTimeout) {
		// TODO Auto-generated method stub
		LOGGER.info("----------------客户端开始启动-------------------------------");
		 bootstrap.group(workerGroup)
	        .channel(NioSocketChannel.class)
	        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
	        .option(ChannelOption.TCP_NODELAY, true)
	        .option(ChannelOption.SO_REUSEADDR,true)
	        .option(ChannelOption.SO_KEEPALIVE, false)
		 	.option(ChannelOption.SO_SNDBUF, 65535)
		 	.option(ChannelOption.SO_RCVBUF, 65535);
		 bootstrap.handler(new ChannelInitializer<SocketChannel>() {
	    		protected void initChannel(SocketChannel channel) throws Exception {
	    			ChannelPipeline pipeline = channel.pipeline();
	    			pipeline.addLast("decoder", new CommonRpcDecoderHandler());
	    			pipeline.addLast("encoder", new CommonRpcEncoderHandler());
	    			pipeline.addLast("handler", new CommonRpcTcpClientHandler());
	    		}

	    	});
		LOGGER.info("----------------客户端启动结束-------------------------------");
	}

	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.client.factory.AbstractRpcClientFactory#createClient(java.lang.String, int, int,boolean)
	 */
	@Override
	protected RpcClient createClient(String targetIP, int targetPort) throws Exception {
		// TODO Auto-generated method stub

			String key="/"+targetIP+":"+targetPort;
		    ChannelFuture future = bootstrap.connect(new InetSocketAddress(targetIP, targetPort)).sync();
		    future.awaitUninterruptibly();
		    if (!future.isDone()) {
		      LOGGER.error("Create connection to " + targetIP + ":" + targetPort + " timeout!");
		      throw new Exception("Create connection to " + targetIP + ":" + targetPort + " timeout!");
		    }
		    if (future.isCancelled()) {
		      LOGGER.error("Create connection to " + targetIP + ":" + targetPort + " cancelled by user!");
		      throw new Exception("Create connection to " + targetIP + ":" + targetPort + " cancelled by user!");
		    }
		    if (!future.isSuccess()) {
		      LOGGER.error("Create connection to " + targetIP + ":" + targetPort + " error", future.cause());
		      throw new Exception("Create connection to " + targetIP + ":" + targetPort + " error", future.cause());
		    }
		    CommonRpcTcpClient client = new CommonRpcTcpClient(future);
		    super.putRpcClient(key, client);
		    return client;
	}

	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.client.factory.AbstractRpcClientFactory#stopClient()
	 */
	@Override
	public void stopClient() throws Exception {
		// TODO Auto-generated method stub
		getInstance().clearClients();
		workerGroup.shutdownGracefully();
	}
	
	public static AbstractRpcClientFactory getInstance() {
	    return _self;
	  }
	
}
