/**
 * 
 */
package com.cross.plateform.common.rpc.core.codec;

/**
 * @author liubing1
 * 解码
 */
public interface CommonRpcDecoder {
	
	/**
	 * decode byte[] to Object
	 */
	public Object decode(String className,byte[] bytes) throws Exception;
}
