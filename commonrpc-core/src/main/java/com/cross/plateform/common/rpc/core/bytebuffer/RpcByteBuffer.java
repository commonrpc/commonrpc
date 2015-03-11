/**
 * 
 */
package com.cross.plateform.common.rpc.core.bytebuffer;

/**
 * @author liubing1
 *
 */
public interface RpcByteBuffer {
	
	public RpcByteBuffer get(int capacity);
	
	public void writeByte(int index,byte data);
	
	public void writeByte(byte data);
	
	public byte readByte();
	
	public void writeInt(int data);
	
	public void writeBytes(byte[] data);
	
	public int readableBytes();
	
	public int readInt();
	
	public void readBytes(byte[] dst);
	
	public int readerIndex();
	
	public void setReaderIndex(int readerIndex);
}
