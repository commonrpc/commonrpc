/**
 * 
 */
package test.cross.plateform.service.impl;

import java.util.List;
import java.util.Map;

import test.cross.plateform.service.DemoBean;
import test.cross.plateform.service.IDemoService;

/**
 * @author liubing1
 *
 */
public class DemoServiceImpl implements IDemoService {

	/* (non-Javadoc)
	 * @see test.cross.plateform.service.IDemoService#sayDemo(java.lang.String, java.lang.String)
	 */
	@Override
	public void sayDemo(String name, String age) {
		// TODO Auto-generated method stub
		System.out.println(name+"--------"+age);
	}

	/* (non-Javadoc)
	 * @see test.cross.plateform.service.IDemoService#sayDemoByParamsMap(java.util.Map)
	 */
	@Override
	public void sayDemoByParamsMap(Map<String, Object> params) {
		// TODO Auto-generated method stub
		System.out.println(params);
	}

	@Override
	public void sayDemoByParamsCollections(List<Object> params) {
		// TODO Auto-generated method stub
		System.out.println(params);
	}

	@Override
	public void sayDemoByParamsArray(Object[] params) {
		// TODO Auto-generated method stub
		System.out.println(params);
	}

	@Override
	public void sayDemoBean(DemoBean demoBean) {
		// TODO Auto-generated method stub
		System.out.println(demoBean.getName()+"--------"+demoBean.getAge());
	}

}
