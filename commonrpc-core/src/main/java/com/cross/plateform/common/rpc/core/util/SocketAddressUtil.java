package com.cross.plateform.common.rpc.core.util;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class SocketAddressUtil {
	
	public static List<InetSocketAddress> getInetSocketAddress(String address){
		List<InetSocketAddress> inetSocketAddresses=new ArrayList<InetSocketAddress>();
		String [] addresses=address.split(",");
		for(String dress:addresses){
			String[] hosts=dress.split(":");
			inetSocketAddresses.add(new InetSocketAddress(hosts[0], Integer.parseInt(hosts[1])));
		}
		return inetSocketAddresses;
	}
}
