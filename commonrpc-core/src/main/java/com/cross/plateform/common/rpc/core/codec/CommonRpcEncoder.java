/**
 * 
 */
package com.cross.plateform.common.rpc.core.codec;

/**
 * @author liubing1
 *
 */
public interface CommonRpcEncoder {
	
	/**
	 * 编码
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public byte[] encode(Object object) throws Exception;
}
