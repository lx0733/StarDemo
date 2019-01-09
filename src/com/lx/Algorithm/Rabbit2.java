package com.lx.Algorithm;

import java.util.Scanner;

import org.junit.Test;

public class Rabbit2 {
	/**
	 * 题目：企业发放的奖金根据利润提成。 利润(I)低于或等于10万元时，奖金可提10%；
	 * 利润高于10万元，低于20万元时，低于10万元的部分按10%提成，高于10万元的部分，可可提成7.5%；
	 * 20万到40万之间时，高于20万元的部分，可提成5%； 40万到60万之间时高于40万元的部分，可提成3%；
	 * 60万到100万之间时，高于60万元的部分，可提成1.5%， 高于100万元时，超过100万元的部分按1%提成，
	 * 从键盘输入当月利润I，求应发放奖金总数？
	 * 
	 * 垃圾提成制度 ,做高业绩已无意义
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		double sum;// 声明要储存的变量应发的奖金
		Scanner input = new Scanner(System.in);// 导入扫描器
		System.out.print("输入当月利润");
		double lirun = input.nextDouble();// 从控制台录入利润
		if (lirun <= 100000) {
			sum = lirun * 0.1;
		} else if (lirun <= 200000) {
			sum = 10000 + lirun * 0.075;
		} else if (lirun <= 400000) {
			sum = 17500 + lirun * 0.05;
		} else if (lirun <= 600000) {
			sum = 27500 + lirun * 0.03;
		} else if (lirun <= 1000000) {
			sum = 33500 + lirun * 0.015;
		} else {
			sum = 39500 + lirun * 0.01;
		}
		System.out.println("应发的奖金是" + sum);
	}

	/**
	 * 题目：一个整数，它加上100后是一个完全平方数，加上168又是一个完全平方数，请问该数是多少？
	 */
	@Test
	public void fun0() {
		for (int i = 1; i < 100000; i++) {
			if (Math.sqrt(i + 100) % 1 == 0 && Math.sqrt(i + 168) % 1 == 0) {
				System.out.println(i);
				// break;
			}
		}
	}

	/**
	 * 题目：输入三个整数x,y,z，请把这三个数由小到大输出。 思路, 两两比较两个数,进行互换, 确保变量顺序就是从小到大
	 */
	@Test
	public void fun01() {
		int i = 2;
		int j = 7;
		int k = 6;
		int x = 0;

		if (i > j) {
			x = i;
			i = j;
			j = x;
		}
		if (i > k) {
			x = i;
			i = k;
			k = x;
		}
		if (j > k) {
			x = j;
			j = k;
			k = x;
		}
		System.out.println(i + ", " + j + ", " + k);
	}

	/**
	 * 题目：输出9*9口诀。
	 */
	@Test
	public void fun02() {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				if (i <= j) {
					System.out.print(i + "*" + j + "=" + i * j + " ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * 题目：猴子吃桃问题：猴子第一天摘下若干个桃子，当即吃了一半，还不过瘾，又多吃了一个
	 * 第二天早上又将剩下的桃子吃掉一半，又多吃了一个。以后每天早上都吃了前一天剩下 的一半零一个。
	 * 到第10天早上想再吃时，见只剩下一个桃子了。求第一天共摘了多少。
	 * 
	 * 思路: 逆向思维
	 * 
	 */
	@Test
	public void fun03() {
		int i1 = 1;
		int x = 0;
		for (int i = 0; i < 10; i++) {
			i1 = (i1 + 1) * 2;
		}
		System.out.println(i1);
	}

	/**
	 * 题目：两个乒乓球队进行比赛，各出三人。 甲队为a,b,c三人，乙队为x,y,z三人。
	 * 已抽签决定比赛名单。有人向队员打听比赛的名单。a说他不和x比，c说他不和x,z比，请编程序找出三队赛手的名单。
	 */
	@Test
	public void fun04() {
		int a = 1;
	}

	/**
	 * 题目：打印出如下图案（菱形）
	 *
	 * 
	 * 
	 * 
	 *** 
	 * 
	 ***** 
	 * 
	 ******* 
	 * 
	 ***** 
	 * 
	 *** 
	 * 
	 *
	 */
	@Test
	public void fun05() {
		for (int y = 7; y >= -7; y--) {
			for (int x = 0; x <= 7; x++) {
				if (x + y <= 7 && x + y >= 0 && y % 2 != 0) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}

	/**
	 * 题目：有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13...求出这个数列的前20项之和。
	 */
	@Test
	public void fun06() {
		double up = 2;
		double down = 1;
		double ori = 1;
		double sum = 0;
		for (int i = 0; i < 20; i++) {
			sum += up / down;
			up = up + down;
			double temp = down;
			down = down + ori;
			ori = temp;
		}
		System.out.println(sum);
	}

	/**
	 * 题目：求1+2!+3!+...+20!的和
	 */
	@Test
	public void fun07() {
		long sum = 0;
		long fac = 1;
		for (int i = 1; i <= 10; i++) {
			fac = fac * i;
			sum += fac;
		}
		System.out.println(sum);
	}

	/**
	 * 题目：利用递归方法求5!。
	 */
	@Test
	public void fun08() {
		System.out.println(multi(5));
	}

	public int multi(int i) {
		if (i <= 1) {
			return i;
		}
		return i * multi(--i);
	}

	/**
	 * 题目：有5个人坐在一起， 问第5个人多少岁？他说比第4个人大2岁。 问第4个人岁数，他说比第3个人大2岁。 问第3个人，又说比第2人大2岁。
	 * 问第2个人，说比第1个人大2岁。 最后问第一个人，他说是10岁。 请问第五个人多大？
	 */
	@Test
	public void fun09() {

	}

	public int add(int i) {
		if (i == 10) {
			return i;
		}
		return i + add(i - 2);
	}

	/**
	 * 题目：给一个不多于5位的正整数，要求：一、求它是几位数，二、逆序打印出各位数字。
	 * 思路:转字符串; 转字符数组然后倒序循环
	 */
	@Test
	public void fun10() {
		int z = 14154;
		System.out.println("是" + String.valueOf(z).length() + "位");
		String s = Long.toString(z);
		char[] ch = s.toCharArray();
		for (int i = ch.length - 1; i >= 0; i--) {
			System.out.print(ch[i]);
		}
	}

}
