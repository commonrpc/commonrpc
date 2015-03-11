/**
 * 
 */
package com.cross.plateform.common.rpc.core.server.handler;

import com.cross.plateform.common.rpc.core.all.message.CommonRpcRequest;
import com.cross.plateform.common.rpc.core.all.message.CommonRpcResponse;
/**
 * @author liubing1
 *
 */
public abstract class AbstractRpcTcpServerHandler implements RpcServerHandler {

	public abstract  CommonRpcResponse handleRequest(CommonRpcRequest request,int codecType,int procotolType );

}
