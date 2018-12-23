package com.lx.other;

public class TestClass {
	public static ReferenceClass rc = new ReferenceClass();
	
	public ReferenceClass getStaticClass(){
		return rc;
	}
	
	public StringBuffer getString(){
		return new StringBuffer("12388");
	}
}
