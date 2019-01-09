package com.lx.Algorithm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Rabbit3 {
	/**
	 * 题目：一个5位数，判断它是不是回文数。即12321是回文数，个位与万位相同，十位与千位相同。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 41214;
		if (i / 10000 == i % 10 && (i / 1000) % 10 == i % 100 / 10) {
			System.out.println("yes");
		}
	}

	/**
	 * 题目：求100之内的素数
	 */
	@Test
	public void fun01() {
		for (int i = 3; i < 101; i++) {
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					break;
				} else if (i % j != 0 && j == Math.floor(Math.sqrt(i))) {
					System.out.println(i + "素数");
				}
			}
		}
	}

	/**
	 * 题目：对10个数进行排序
	 */
	@Test
	public void fun02() {
		// 冒泡排序 前后两个数 两两比较 先把最大的放后面, 第二大的放倒数第二个位置 比较次数=数组长度-1
		int[] arr = { 5, 4, 1, 67, 34, 77, 96, 36, 38, 3, 80, 99, 53, 50, 41, 64 };
		for (int j = arr.length; j > 0; j--) {
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
		}

		// 选择排序 就是拿每个数的后面的数与他对比,小于他的放前面来, 然后拿前面新交换的较小数再与后面的比较
		// 直到把最小的放0号位,第二小的放1号位
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					int temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
		// 快速排序 就是分治排序 任意取一个数 大于他的放右边 小于他的放左边
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * 题目：求一个3*3矩阵对角线元素之和
	 */
	@Test
	public void fun03() {
		double sum = 0;
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 7, 8 } };
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (i == j)
					sum = sum + array[i][j];
			}
		System.out.println(sum);
	}
	/**
	 * 题目：有一个已经排好序的数组。现输入一个数，要求按原来的规律将它插入数组中。  
	 */
	@Test
	public void fun04() {
		int[] arr = { 1, 5, 8, 34, 77, 96 };
		int[] newArr = new int[arr.length+1]; 
		System.arraycopy(arr, 0, newArr, 0, arr.length);
		int x = 24;
		for (int i = 0; i < newArr.length; i++) {
			if (x < newArr[i]) {
				for (int j = newArr.length; j > i; j--) {
					newArr[j-1] = newArr[j-2];
				}
				newArr[i] = x;
				break;
			}
		}
		for (int i = 0; i < newArr.length; i++) {
			System.out.println(newArr[i]);
		}
	}
	
	/**
	 * 题目：打印出杨辉三角形（要求打印出10行如下图）  
	 * 
	1  
	1   1  
	1   2   1  
	1   3   3   1  
	1   4   6   4   1  
	1   5   10  10  5   1  
	 */
	@Test
	public void fun05() {
		int i, j;
		int a[][];
		a = new int[8][8];
		for (i = 0; i < 8; i++) {
			a[i][i] = 1;
			a[i][0] = 1;
		}
		for (i = 2; i < 8; i++) {
			for (j = 1; j <= i - 1; j++) {
				a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
			}
		}
		for (i = 0; i < 8; i++) {
			for (j = 0; j < i; j++) {
				System.out.printf("  " + a[i][j]);
			}
			System.out.println();
		}
	}
	/**
	 * 题目：有n个整数，使其前面各数顺序向后移m个位置，最后m个数变成最前面的m个数  
	 * 思路:不带条件的冒泡  三次就行
	 */
	@Test
	public void fun06(){
		int[] arr = { 1, 5, 8, 34, 77, 96 };
		int x = 3;
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < arr.length-1; j++) {
				int temp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = temp;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	/**
	 * 题目：有n个人围成一圈，顺序排号。
	 * 从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，问最后留下的是原来第几号的那位。  
	 * @throws InterruptedException 
	 */
	@Test
	public void fun07() throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");

		int count = 1;
		int s=1;
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list);
			System.out.println("序号"+count+"数字"+list.get(i));
			if (count == 3) {
				System.out.println("序号"+count+",位数"+i+",删除"+list.get(i));
				list.remove(i);
				count = 1;
				count --;
			}
			count ++;
			if (i>=list.size()) {
				i = 0;
			}
			if (list.size() ==1) {
				break;
			}
			
		}
		Thread.sleep(1000);
		System.err.println(list);
	}
	/**
	 * 题目：编写一个函数，输入n为偶数时，调用函数求1/2+1/4+...+1/n,当输入n为奇数时，调用函数1/1+1/3+...+1/n
	 */
	@Test
	public void fun08(){
		double n = 4;
		double sum = 0;
		for (double i = n%2==0?2:1; i <= n; i=i+2) {
			sum += 1/i;
		}
		System.out.println(sum);
	}
	/**
	 * 题目：海滩上有一堆桃子，五只猴子来分。
	 * 第一只猴子把这堆桃子凭据分为五份，多了一个，这只猴子把多的一个扔入海中，拿走了一份。
	 * 第二只猴子把剩下 的桃子又平均分成五份，又多了一个，它同样把多的一个扔入海中，拿走了一份，
	 * 第三、第四、第五只猴子都是这样做的，问海滩上原来 最少 有多少个桃子？
	 */
	@Test
	public void fun09(){
		int i, m, j = 0, k, count;
	    for (i = 4; i < 10000; i += 4) {
	        count = 0;
	        m = i;
	        for (k = 0; k < 5; k++) {
	            j = i / 4 * 5 + 1;
	            i = j;
	            if (j % 4 == 0)
	                count++;
	            else
	                break;
	        }
	        i = m;
	        if (count == 4) {
	            System.out.println("原有桃子 " + j + " 个");
	            break;
	        }
	    }

	}
	

}
