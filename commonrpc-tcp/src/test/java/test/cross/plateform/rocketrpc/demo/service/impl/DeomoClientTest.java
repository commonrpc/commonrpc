/**
 * 
 */
package test.cross.plateform.rocketrpc.demo.service.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.cross.plateform.rocketrpc.demo.service.IDemoService;

/**
 * @author liubing1
 *
 */
public class DeomoClientTest {
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("CommonRpcClient.xml");
		IDemoService demoService=(IDemoService) context.getBean("demoServiceClient");
		long time1=System.currentTimeMillis();
		for(int i=0;i<10;i++){
			String result=demoService.sayDemo("okok");
			System.out.println(result);
		}
		
		long end1=System.currentTimeMillis();
		System.out.println("完成时间1:"+(end1-time1));
		
	}
}
