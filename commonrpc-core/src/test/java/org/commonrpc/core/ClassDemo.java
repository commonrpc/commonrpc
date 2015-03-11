package org.commonrpc.core;

public class ClassDemo {
	
	public String  sayDemo(){
		for(int i=0;i<100;i++){
			for(int j=0;j<50;j++){
				if(j==40){
					return "40";
				}
			}
		}
		return null;
	}
	public static void main(String[] args) throws Exception{
		
		System.out.println(new ClassDemo().sayDemo());
		

	}
}
