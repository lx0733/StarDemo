package com.lx.other;

public class TestClass {
	/**
	 * 执行(赋值)顺序  
	 * 1.final赋值     类加载的准备阶段 , 不会赋零值  直接赋定义的值
	 * 2.static字段赋零值(int = 0) 类加载的准备阶段
	 * 3.static代码块赋值  <clinit>
	 * 4.static字段赋定义时的值  <clinit>
	 */
	static{
		System.out.println("执行了<clinit>方法");
		i = 3;
	}
	public static ReferenceClass rc = new ReferenceClass(); //引用类型字段 即使加上final修饰也会触发类加载的初始化 
	
	/*直接引用final的常量不会触发类的初始化(即:不会执行<clinit>方法  也就不会执行上面的static代码块), 
	 * 因为final修饰后 8个基本类型和String直接加入了被引用类的,
	*/
	public final static String str = "sss"; 
	
	public static int i = 2; //如果此时不赋值(零值 0) 打印i的值是3 
	
	
	public ReferenceClass getStaticClass(){
		return rc;
	}
	
	public StringBuffer getString(){
		return new StringBuffer("12388");
	}
}
