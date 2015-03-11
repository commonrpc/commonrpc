/**
 * 
 */
package com.cross.plateform.common.rpc.tcp.netty4.spring.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.cross.plateform.common.rpc.tcp.netty4.spring.config.support.CommonRpcService;
/**
 * @author liubing1
 *
 */
public class CommonRpcServiceParser implements BeanDefinitionParser {
	
	public CommonRpcServiceParser(){
		
	}
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.xml.BeanDefinitionParser#parse(org.w3c.dom.Element, org.springframework.beans.factory.xml.ParserContext)
	 */
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// TODO Auto-generated method stub
		String interfacename = element.getAttribute("interfacename");
		String ref=element.getAttribute("ref");
		String filterRef=element.getAttribute("filterRef");
		
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(CommonRpcService.class);
		beanDefinition.setLazyInit(false);
        beanDefinition.getPropertyValues().addPropertyValue("interfacename", interfacename);
        beanDefinition.getPropertyValues().addPropertyValue("ref", ref);
        beanDefinition.getPropertyValues().addPropertyValue("filterRef", filterRef);
        
        parserContext.getRegistry().registerBeanDefinition(interfacename, beanDefinition);
		return beanDefinition;
	}

}
