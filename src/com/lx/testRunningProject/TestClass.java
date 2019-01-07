package com.lx.testRunningProject;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
	public static void main(String[] args) {
		/*method(new ArrayList<Integer>());
		method("1");
		for (int i = 0; i < 10; i++) {
			if (i == 5) {
				try {
					throw new RuntimeException("测试异常");
				} catch (Exception e) {
					System.out.println("i=" + i + ",exception");
				}
			}
		}*/
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		System.out.println(c == d);
		System.out.println(e == f);
		System.out.println(c == (a + b));
		System.out.println(c.equals(a + b));
		System.out.println(g == (a + b));
		System.out.println(g.equals(a + b));
	}

	public static Integer method(List<Integer> as) {
		int i = 0;
		System.out.println("method integer");
		return i;
	}

	public static String method(String as) {
		int i1 = 0;
		System.out.println("method string");
		return null;
	}
}
