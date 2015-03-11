/**
 * 
 */
package test.cross.plateform.rocketrpc.demo.service.impl;

import java.lang.reflect.Method;

import com.cross.plateform.common.rpc.server.filter.RpcFilter;

/**
 * @author liubing1
 *
 */
public class DemoRpcFilter implements RpcFilter {

	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.server.filter.RpcFilter#doBeforeRequest(java.lang.reflect.Method, java.lang.Object, java.lang.Object[])
	 */
	@Override
	public boolean doBeforeRequest(Method method, Object processor,
			Object[] requestObjects) {
		// TODO Auto-generated method stub
		System.out.println("----------------拦截开始----------------");
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cross.plateform.common.rpc.server.filter.RpcFilter#doAfterRequest(java.lang.Object)
	 */
	@Override
	public boolean doAfterRequest(Object processor) {
		// TODO Auto-generated method stub
		System.out.println("----------------拦截结束----------------");
		return false;
	}
	
	
}
