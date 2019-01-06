package com.lx.other;

public class TestClinit {
	//private final TestClass tc;
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		System.out.println(TestClass.class.newInstance());
		//System.out.println(ReferenceClass.css);
		//System.out.println(new ReferenceClass().ss);
	}
}
