/**
 * 
 */
package test.cross.plateform.service;

import java.util.List;
import java.util.Map;

/**
 * @author liubing1
 *
 */
public interface IDemoService {
	
	
	public void sayDemo(String name,String age);
	
	public void sayDemoByParamsMap(Map<String, Object> params);
	
	public void sayDemoByParamsCollections(List<Object> params);
	
	public void sayDemoByParamsArray(Object[] params);
	
	
	public void sayDemoBean(DemoBean demoBean);
}
