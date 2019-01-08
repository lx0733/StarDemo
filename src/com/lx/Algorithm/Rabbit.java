package com.lx.Algorithm;

import org.junit.Test;

public class Rabbit {
	/**
	 * 有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第四个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？ 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(birth(5));
	}
	
	public static int birth(int x){
		if(x==1 || x==2) {
            return 1;
		}else{
            return birth(x-1)+birth(x-2);
		}
	}
	/**
	 * 题目：判断101-200之间有多少个质数，并输出所有质数。 
	 * 思路:除以2至X/2 看是否有余数 
	 */
	@Test
	public void fun01(){
		out:for (int i = 101; i < 201; i++) {
			boolean flag = false;
			in:for (int j = 2; j <= i/2; j++) {
				if (i % j != 0 && j == i/2) {
					System.out.println(i+"是个质数");
				}else{
					//不是质数 直接跳出
					break in;
				}
			}
		}
		//System.out.println(Math.floor(101/2));
	}
	/**
	 * 题目：打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。例如：153是一个 "水仙花数 "，因为153=1的三次方＋5的三次方＋3的三次方。 
	 */
	@Test
	public void fun02(){
		for (int i = 100; i < 1000; i++) {
			int x = i/100;  // 百位
			int y = (i - (x*100))/10;  
			int z = i - x*100 - y*10; 
			if ((x*x*x +y*y*y +z*z*z) == i) {
				 System.out.println(i + "是个水仙花数");
			}
		}
	}
	/**
	 * 题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
	 */
	@Test
	public void fun03(){
		
	}
	
}
