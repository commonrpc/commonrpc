/**
 * 
 */
package com.cross.plateform.common.rpc.http.netty4.server.bean;

import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultHttpResponse;

import java.io.Serializable;

/**
 * @author liubing1
 *
 */
public class ServerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1066483530993789009L;
	
	private DefaultHttpResponse response;
	
	private DefaultHttpContent defaultHttpContent;

	/**
	 * @return the response
	 */
	public DefaultHttpResponse getResponse() {
		return response;
	}
	
	
	public ServerBean(DefaultHttpResponse response,
			DefaultHttpContent defaultHttpContent) {
		super();
		this.response = response;
		this.defaultHttpContent = defaultHttpContent;
	}


	/**
	 * @param response the response to set
	 */
	public void setResponse(DefaultHttpResponse response) {
		this.response = response;
	}

	/**
	 * @return the defaultHttpContent
	 */
	public DefaultHttpContent getDefaultHttpContent() {
		return defaultHttpContent;
	}

	/**
	 * @param defaultHttpContent the defaultHttpContent to set
	 */
	public void setDefaultHttpContent(DefaultHttpContent defaultHttpContent) {
		this.defaultHttpContent = defaultHttpContent;
	}
	
	
}
