/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.spring.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
/**
 * @author liubing1
 *
 */
public class CommonRpcNamespaceHandler extends NamespaceHandlerSupport {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.xml.NamespaceHandler#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		registerBeanDefinitionParser("reference", new CommonRpcReferenceParser());
		registerBeanDefinitionParser("service", new CommonRpcServiceParser());
		registerBeanDefinitionParser("registry", new CommonRpcRegisteryParser());
	}

}
