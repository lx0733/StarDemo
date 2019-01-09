package com.lx.Algorithm;

import org.junit.Test;

public class Rabbit3 {
	/**
	 * 题目：一个5位数，判断它是不是回文数。即12321是回文数，个位与万位相同，十位与千位相同。 
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 41214;
		if (i/10000 == i%10 && (i/1000)%10 == i%100/10) {
			System.out.println("yes");
		}
	}
	/**
	 * 题目：求100之内的素数    
	 */
	@Test
	public void fun01(){
		for (int i = 3; i < 101; i++) {
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					continue;
				}else{
					System.out.println(i+"素数");
				}
			}
		}
	}
}
