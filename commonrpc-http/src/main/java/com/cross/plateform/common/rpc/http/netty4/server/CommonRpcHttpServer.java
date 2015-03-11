/**
 * 
 */
package com.cross.plateform.common.rpc.http.netty4.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cross.plateform.common.rpc.core.server.RpcServer;
import com.cross.plateform.common.rpc.core.server.handler.factory.CommonRpcServerHandlerFactory;
import com.cross.plateform.common.rpc.core.thread.NamedThreadFactory;
import com.cross.plateform.common.rpc.http.netty4.server.handler.CommonRpcHttpServerHander;
import com.cross.plateform.common.rpc.server.filter.RpcFilter;

/**
 * @author liubing1
 *
 */
public class CommonRpcHttpServer implements RpcServer {
	
	private static final Log LOGGER = LogFactory.getLog(CommonRpcHttpServer.class);
	
	private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
	
	private EventLoopGroup bossGroup;
	
	private NioEventLoopGroup workerGroup;
	
	public CommonRpcHttpServer() {

	}

	private static class SingletonHolder {
		static final CommonRpcHttpServer instance = new CommonRpcHttpServer();
	}

	public static CommonRpcHttpServer getInstance() {
		return SingletonHolder.instance;
	}
	
	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.server.RpcServer#registerProcessor(java.lang.String, java.lang.Object, com.cross.plateform.common.rpc.server.filter.RpcFilter)
	 */
	@Override
	public void registerProcessor(String serviceName, Object serviceInstance,
			RpcFilter rpcFilter) {
		// TODO Auto-generated method stub
		CommonRpcServerHandlerFactory.getHttpServerHandler().registerProcessor(serviceName, serviceInstance, rpcFilter);
	}

	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.server.RpcServer#stop()
	 */
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		CommonRpcServerHandlerFactory.getHttpServerHandler().clear();
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}

	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.core.server.RpcServer#start(int, int)
	 */
	@Override
	public void start(int port, final int timeout) throws Exception {
		// TODO Auto-generated method stub
		ThreadFactory serverBossTF = new NamedThreadFactory("NETTYSERVER-BOSS-");
	    ThreadFactory serverWorkerTF = new NamedThreadFactory("NETTYSERVER-WORKER-");
	    bossGroup = new NioEventLoopGroup(PROCESSORS, serverBossTF);
	    workerGroup = new NioEventLoopGroup(PROCESSORS * 2, serverWorkerTF);
	    workerGroup.setIoRatio(80);
	    ServerBootstrap bootstrap = new ServerBootstrap();
	    bootstrap.group(bossGroup, workerGroup)
	    .channel(NioServerSocketChannel.class)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
        .option(ChannelOption.SO_BACKLOG, 1024)
        .option(ChannelOption.SO_REUSEADDR,true)
        .option(ChannelOption.SO_KEEPALIVE, true)
	 	.option(ChannelOption.SO_SNDBUF, 65535)
	 	.option(ChannelOption.SO_RCVBUF, 65535)
	 	.childOption(ChannelOption.TCP_NODELAY, true);
	    
	    bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

	        protected void initChannel(SocketChannel channel) throws Exception {
	          ChannelPipeline pipeline = channel.pipeline();
	          pipeline.addLast("codec", new HttpServerCodec());
	          pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));
	          pipeline.addLast("biz", new CommonRpcHttpServerHander(timeout));
	        }

	      });
	   LOGGER.info("-----------------开始启动--------------------------");
	   bootstrap.bind(new InetSocketAddress(port)).sync();
	   LOGGER.info("端口号："+port+"的服务端已经启动,作者:liubing");
	   LOGGER.info("-----------------启动结束--------------------------");
	}

}
