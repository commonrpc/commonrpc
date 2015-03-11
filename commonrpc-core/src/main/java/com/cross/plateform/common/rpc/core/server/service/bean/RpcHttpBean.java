/**
 * 
 */
package com.cross.plateform.common.rpc.core.server.service.bean;

import java.io.Serializable;

/**
 * @author liubing1
 *
 */
public class RpcHttpBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 856548453845561188L;
	
	private Object object;
	
	private String httpType;
	
	private String returnType;

	
	public RpcHttpBean(Object object, String httpType, String returnType) {
		super();
		this.object = object;
		this.httpType = httpType;
		this.returnType = returnType;
	}

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * @return the httpType
	 */
	public String getHttpType() {
		return httpType;
	}

	/**
	 * @param httpType the httpType to set
	 */
	public void setHttpType(String httpType) {
		this.httpType = httpType;
	}

	/**
	 * @return the returnType
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * @param returnType the returnType to set
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	
}
