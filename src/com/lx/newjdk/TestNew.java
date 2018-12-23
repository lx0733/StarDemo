package com.lx.newjdk;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestNew {
	/**
	 * 测试新特性 方法与构造函数引用 ::
	 */
	@Test
	 public void fun(){
		 Jdk8Interface jI = (s1,s2) -> System.out.println(s1+s2);
		 Jdk8Interface jI2 = Arrays::asList;
		 String[] ss = {"9","999"};
		 jI.method("1", "3");
		 
		 
		 FuncInterface fi = Arrays::asList; 
		 System.out.println((fi.process(ss)) instanceof List);
	}
	
}
