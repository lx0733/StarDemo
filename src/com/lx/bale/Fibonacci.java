package com.lx.bale;

public class Fibonacci implements Cloneable{
	private static int curNum = 1;
	private static int preNum = 0;
	
	public static int getNextValue(){
		int temp = preNum;
		preNum = curNum ;
		curNum = curNum +temp;
		return curNum;
	}
	
	public static void initValue(){
		curNum = 1;
		preNum = 0;
	}
	
	
}
