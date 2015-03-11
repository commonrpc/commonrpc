/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.spring.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.cross.plateform.common.rpc.tcp.netty4.spring.config.support.CommonRpcReference;
/**
 * @author liubing1
 *
 */
public class CommonRpcReferenceParser implements BeanDefinitionParser {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.xml.BeanDefinitionParser#parse(org.w3c.dom.Element, org.springframework.beans.factory.xml.ParserContext)
	 */
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// TODO Auto-generated method stub
		String interfacename = element.getAttribute("interfacename");
		String id = element.getAttribute("id");
		String address=element.getAttribute("address");
		int procotolType=Integer.parseInt(element.getAttribute("procotolType"));
		int codecType=Integer.parseInt(element.getAttribute("codecType"));
		int timeout=Integer.parseInt(element.getAttribute("timeout"));
		String token=element.getAttribute("token");
		
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(CommonRpcReference.class);
		beanDefinition.setLazyInit(false);
		
		beanDefinition.getPropertyValues().addPropertyValue("interfacename", interfacename);
        beanDefinition.getPropertyValues().addPropertyValue("address", address);
        beanDefinition.getPropertyValues().addPropertyValue("protocolType", procotolType);
        beanDefinition.getPropertyValues().addPropertyValue("codecType", codecType);
        beanDefinition.getPropertyValues().addPropertyValue("timeout", timeout);
        beanDefinition.getPropertyValues().addPropertyValue("token", token);
        
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		return beanDefinition;
	}

}
