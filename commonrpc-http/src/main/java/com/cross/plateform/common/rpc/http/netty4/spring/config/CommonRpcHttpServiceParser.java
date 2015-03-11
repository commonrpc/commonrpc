/**
 * 
 */
package com.cross.plateform.common.rpc.http.netty4.spring.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.cross.plateform.common.rpc.http.netty4.spring.config.support.CommonRpcHttpService;
/**
 * @author liubing1
 *
 */
public class CommonRpcHttpServiceParser implements BeanDefinitionParser {
	
	public CommonRpcHttpServiceParser(){
		
	}
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.xml.BeanDefinitionParser#parse(org.w3c.dom.Element, org.springframework.beans.factory.xml.ParserContext)
	 */
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// TODO Auto-generated method stub
		String projectname = element.getAttribute("projectname");
		String ref=element.getAttribute("ref");
		String filterRef=element.getAttribute("filterRef");
		String httpType=element.getAttribute("httpType");
		String returnType=element.getAttribute("returnType");
		
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(CommonRpcHttpService.class);
		beanDefinition.setLazyInit(false);
        beanDefinition.getPropertyValues().addPropertyValue("projectname", projectname);
        beanDefinition.getPropertyValues().addPropertyValue("ref", ref);
        beanDefinition.getPropertyValues().addPropertyValue("filterRef", filterRef);
        beanDefinition.getPropertyValues().addPropertyValue("httpType", httpType);
        beanDefinition.getPropertyValues().addPropertyValue("returnType", returnType);
        
        parserContext.getRegistry().registerBeanDefinition(projectname, beanDefinition);
		return beanDefinition;
	}

}
