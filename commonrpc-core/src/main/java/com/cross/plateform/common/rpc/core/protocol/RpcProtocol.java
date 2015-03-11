/**
 * 
 */
package com.cross.plateform.common.rpc.core.protocol;

import com.cross.plateform.common.rpc.core.bytebuffer.RpcByteBuffer;
/**
 * @author liubing1
 * rocket rpc 协议接口
 */
public interface RpcProtocol {
	
	/**
	 * 编码
	 * @param message
	 * @param bytebufferWrapper
	 * @return
	 * @throws Exception
	 */
	public RpcByteBuffer encode(Object message,RpcByteBuffer bytebufferWrapper) throws Exception;

	/**
	 * 解码
	 * @param wrapper
	 * @param errorObject
	 * @param originPos
	 * @return
	 * @throws Exception
	 */
	public Object decode(RpcByteBuffer wrapper, Object errorObject,int...originPos) throws Exception;
}
