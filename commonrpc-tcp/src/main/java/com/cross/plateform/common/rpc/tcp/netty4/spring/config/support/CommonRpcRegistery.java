/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.spring.config.support;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.cross.plateform.common.rpc.tcp.netty4.server.CommonRpcTcpServer;
/**
 * @author liubing1
 * redis 存储地址
 */
public class CommonRpcRegistery implements InitializingBean, DisposableBean {

	private int port;//端口号
	
	private int timeout;
	
	private String token;
	
	private int procotolType;//协议名称
	
	private int codecType;//编码类型
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		CommonRpcTcpServer.getInstance().stop();//停止
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if(port==0){
			throw new Exception("parameter  timeout port can not be null");
		}
		CommonRpcTcpServer.getInstance().setToken(token);
		CommonRpcTcpServer.getInstance().setCodecType(codecType);
		CommonRpcTcpServer.getInstance().setProcotolType(procotolType);
		CommonRpcTcpServer.getInstance().start(port,timeout);
	}
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the procotolType
	 */
	public int getProcotolType() {
		return procotolType;
	}
	/**
	 * @param procotolType the procotolType to set
	 */
	public void setProcotolType(int procotolType) {
		this.procotolType = procotolType;
	}
	/**
	 * @return the codecType
	 */
	public int getCodecType() {
		return codecType;
	}
	/**
	 * @param codecType the codecType to set
	 */
	public void setCodecType(int codecType) {
		this.codecType = codecType;
	}
	
	
}
