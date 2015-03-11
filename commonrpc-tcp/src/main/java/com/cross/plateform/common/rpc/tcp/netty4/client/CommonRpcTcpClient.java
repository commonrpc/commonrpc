package com.cross.plateform.common.rpc.tcp.netty4.client;


import java.net.InetSocketAddress;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cross.plateform.common.rpc.core.all.message.CommonRpcRequest;
import com.cross.plateform.common.rpc.core.all.message.CommonRpcResponse;
import com.cross.plateform.common.rpc.core.client.AbstractRpcClient;
import com.cross.plateform.common.rpc.core.client.factory.RpcClientFactory;
import com.cross.plateform.common.rpc.tcp.netty4.client.factory.CommonRpcTcpClientFactory;
public class CommonRpcTcpClient extends AbstractRpcClient {
	
	private static final Log LOGGER = LogFactory.getLog(CommonRpcTcpClient.class);

	private ChannelFuture cf;

	public CommonRpcTcpClient(ChannelFuture cf) {
		this.cf = cf;
	}
	
	@Override
	public String getServerIP() {
		// TODO Auto-generated method stub
		return ((InetSocketAddress) cf.channel().remoteAddress()).getHostName();
	}

	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return ((InetSocketAddress) cf.channel().remoteAddress()).getPort();
	}

	@Override
	public RpcClientFactory getClientFactory() {
		// TODO Auto-generated method stub
		return CommonRpcTcpClientFactory.getInstance();
	}

	@Override
	public void sendRequest(final CommonRpcRequest commonRpcRequest)
			throws Exception {
		// TODO Auto-generated method stub
		if(cf.channel().isOpen()){
			ChannelFuture writeFuture = cf.channel().writeAndFlush(commonRpcRequest);
		    // use listener to avoid wait for write & thread context switch
		    writeFuture.addListener(new ChannelFutureListener() {
		      public void operationComplete(ChannelFuture future)
		          throws Exception {
		        if (future.isSuccess()) {
		          return;
		        }
		        String errorMsg = "";
		        // write timeout
		        
		        if (future.isCancelled()) {
		          errorMsg = "Send request to " + cf.channel().toString()
		              + " cancelled by user,request id is:"
		              + commonRpcRequest.getId();
		        }else if (!future.isSuccess()) {
		          if (cf.channel().isOpen()) {
		            // maybe some exception,so close the channel
		            cf.channel().close();
		            getClientFactory().removeRpcClient(cf.channel().remoteAddress().toString());
		          }
		          errorMsg = "Send request to " + cf.channel().toString() + " error" + future.cause();
		        }
		        LOGGER.error(errorMsg);
		        CommonRpcResponse response =
		            new CommonRpcResponse(commonRpcRequest.getId(), commonRpcRequest.getCodecType(), commonRpcRequest.getProtocolType());
		        response.setException(new Exception(errorMsg));
		        getClientFactory().receiveResponse(response);
		      }
		    });
		}
	    
	}

}
