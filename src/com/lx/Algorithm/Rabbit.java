package com.lx.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class Rabbit {
	/**
	 * 有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第四个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(birth(5));
	}

	public static int birth(int x) {
		if (x == 1 || x == 2) {
			return 1;
		} else {
			return birth(x - 1) + birth(x - 2);
		}
	}

	/**
	 * 题目：判断101-200之间有多少个质数，并输出所有质数。 思路:除以2至X/2 看是否有余数
	 */
	@Test
	public void fun01() {
		out: for (int i = 101; i < 201; i++) {
			boolean flag = false;
			in: for (int j = 2; j <= i / 2; j++) {
				if (i % j == 0) {
					// 不是质数 直接跳出
					break in;
				} else if (j == i / 2) {
					System.out.println(i + "是个质数");
				}
			}
		}
		// System.out.println(Math.floor(101/2));
	}

	/**
	 * 题目：打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。例如：153是一个 "水仙花数 "
	 * ，因为153=1的三次方＋5的三次方＋3的三次方。 思路: 分别取百位,十位,个位
	 */
	@Test
	public void fun02() {
		for (int i = 100; i < 1000; i++) {
			int x = i / 100; // 百位
			int y = (i - (x * 100)) / 10;
			int z = i - x * 100 - y * 10;
			if ((x * x * x + y * y * y + z * z * z) == i) {
				System.out.println(i + "是个水仙花数");
			}
		}
	}

	/**
	 * 题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
	 * 思路:不用纠结于质数,直接从小往大除就行,因为如果有非质数因子也是先被该因子的因子除掉
	 */
	@Test
	public void fun03() {
		List<Integer> list = new ArrayList<>();
		int i = 90;
		for (int j = 2; j <= i; j++) {
			if (i % j == 0) {
				list.add(j);
				i = i / j;
				j--;
			}
		}
		System.out.println(list);
	}

	/**
	 * 上面题目使用递归
	 */
	@Test
	public void fun04() {
		resolve(630);
	}

	public void resolve(int i) {
		if (i == 2) {
			return;
		}
		for (int j = 2; j <= i; j++) {
			if (i % j == 0) {
				System.out.print(j + "*");
				resolve(i / j);
				break;
			}
		}
	}

	/**
	 * 题目：输入两个正整数m和n，求其最大公约数和最小公倍数。
	 */
	@Test
	public void fun05() {
		int max = 320;
		int min = 240;
		System.out.println(commonDivisor(max, min));
		System.out.println(gcd(max, min));
		System.out.println(max * min / commonDivisor(max, min));
	}

	// 最小公约数 辗除法
	int commonDivisor(int M, int N) {
		if (N < 0 || M < 0) {
			return -1;
		}
		if (N == 0) {
			return M;
		}
		return commonDivisor(N, M % N);
	}

	int gcd(int m, int n) {
		while (true) {
			if ((m = m % n) == 0)
				return n;
			if ((n = n % m) == 0)
				return m;
		}
	}

	/**
	 * 输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
	 */
	@Test
	public void fun06() {
		System.out.println("请输入字符串：");
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		String E1 = "[\u4e00-\u9fa5]";
		String E2 = "[a-zA-Z]";
		int countH = 0;
		int countE = 0;
		char[] arrChar = str.toCharArray();
		String[] arrStr = new String[arrChar.length];
		for (int i = 0; i < arrChar.length; i++) {
			arrStr[i] = String.valueOf(arrChar[i]);
		}
		for (String i : arrStr) {
			if (i.matches(E1)) {
				countH++;
			}
			if (i.matches(E2)) {
				countE++;
			}
		}
		System.out.println("汉字的个数" + countH);
		System.out.println("字母的个数" + countE);

	}

	/**
	 * 题目：求s=a+aa+aaa+aaaa+aa...a的值，其中a是一个数字。
	 * 例如2+22+222+2222+22222(此时共有5个数相加)，几个数相加有键盘控制。
	 * 
	 * @throws IOException
	 */
	@Test
	public void fun07() throws IOException {
		int s = 0;
		String output = "";
		BufferedReader stadin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入a的值");
		String input = stadin.readLine();
		for (int i = 1; i <= Integer.parseInt(input); i++) {
			output += input;// 拼接字符串 哈哈哈哈
			System.err.println(output);
			int a = Integer.parseInt(output);
			System.err.println(a);
			s += a;
		}
		System.out.println(s);
	}

	/**
	 * \题目：一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。例如6=1＋2＋3.编程 找出1000以内的所有完数。
	 */
	@Test
	public void fun08() {
		int s;
		for (int i = 1; i <= 1000; i++) {
			s = 0;
			for (int j = 1; j < i; j++)
				if (i % j == 0)
					s = s + j;
			if (s == i)
				System.out.print(i + " ");
		}
		System.out.println();
	}

	/**
	 * 有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
	 */
	@Test
	public void fun09() {
		int i = 0;
		int j = 0;
		int k = 0;
		int t = 0;
		for (i = 1; i <= 4; i++)
			for (j = 1; j <= 4; j++)
				for (k = 1; k <= 4; k++)
					if (i != j && j != k && i != k) {
						t += 1;
						System.out.println(i * 100 + j * 10 + k);
					}
		System.out.println(t);
	}
}
