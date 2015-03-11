/**
 * 
 */
package test.cross.plateform.rocketrpc.demo.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import test.cross.plateform.rocketrpc.demo.service.IDemoService;

/**
 * @author liubing1
 *
 */
public class DemoServiceImpl implements IDemoService {
	
	private static final Log LOGGER = LogFactory
			.getLog(DemoServiceImpl.class);
	
	@Override
	public String sayDemo(String params) {
		// TODO Auto-generated method stub
		//LOGGER.info("sayDemo params:"+params);
		return "from server:"+params;
	}



	@Override
	public String getParam(String params) {
		// TODO Auto-generated method stub
		//LOGGER.info("getParam params:"+params);
		return "from server:"+params;
	}

}
