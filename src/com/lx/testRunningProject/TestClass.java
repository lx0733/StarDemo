package com.lx.testRunningProject;

public class TestClass {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			if (i == 5) {
				try {
					throw new RuntimeException("测试异常");
				} catch (Exception e) {
					System.out.println("i="+i+",exception");
				}
			}
		}
	}
}
