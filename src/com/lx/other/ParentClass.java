package com.lx.other;

public class ParentClass {
	static{
		ss = 2; 
		/*if (true) {
			throw new RuntimeException();
		}*/
	}
	public static int ss = 5;
	
	public static int add(int s){
		if (true) {
			throw new RuntimeException();
		}
		return s+5;
	};
	
	public void sys(){
		System.out.println("sadasd");
	}
}
