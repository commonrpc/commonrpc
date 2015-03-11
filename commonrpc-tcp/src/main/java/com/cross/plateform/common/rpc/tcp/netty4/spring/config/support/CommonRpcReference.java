/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.spring.config.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import com.cross.plateform.common.rpc.tcp.netty4.client.factory.CommonRpcTcpClientFactory;
import com.cross.plateform.common.rpc.tcp.netty4.client.proxy.CommonRpcTcpClientProxy;
/**
 * @author liubing1
 *
 */
public class CommonRpcReference implements FactoryBean, InitializingBean,
		DisposableBean {
	
	/**
	 * 接口名称
	 */
	private String interfacename;
	
	/**
	 * 超时时间
	 */
	private int timeout;
	/**
	 * ip:port,
	 */
	private String address;
	/**
	 * 编码类型
	 */
	private int codecType;
	/**
	 * 协议类型
	 */
	private int protocolType;
	
	private String token ;
	
	private static final Log LOGGER = LogFactory.getLog(CommonRpcReference.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		CommonRpcTcpClientFactory.getInstance().stopClient();
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		CommonRpcTcpClientFactory.getInstance().startClient(timeout);
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public Object getObject() throws Exception {
		// TODO Auto-generated method stub
		return CommonRpcTcpClientProxy.getInstance().getProxyService(getObjectType(), timeout, codecType, protocolType, getObjectType().getName(), address,token);
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(interfacename);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.error("spring 解析失败", e);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @return the interfacename
	 */
	public String getInterfacename() {
		return interfacename;
	}

	/**
	 * @param interfacename the interfacename to set
	 */
	public void setInterfacename(String interfacename) {
		this.interfacename = interfacename;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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

	/**
	 * @return the protocolType
	 */
	public int getProtocolType() {
		return protocolType;
	}

	/**
	 * @param protocolType the protocolType to set
	 */
	public void setProtocolType(int protocolType) {
		this.protocolType = protocolType;
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
	
	
}
