/**
 * 
 */
package com.cross.plateform.common.rpc.http.netty4.spring.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
/**
 * @author liubing1
 *
 */
public class CommonRpcHttpNamespaceHandler extends NamespaceHandlerSupport {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.xml.NamespaceHandler#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		registerBeanDefinitionParser("service", new CommonRpcHttpServiceParser());
		registerBeanDefinitionParser("registry", new CommonRpcHttpRegisteryParser());
	}

}
