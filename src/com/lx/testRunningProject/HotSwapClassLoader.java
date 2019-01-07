package com.lx.testRunningProject;

/**
 * 为了多次执行加载类而  自定义的加载器  用于外部显示调用类加载
 * 开放defineClass方法, 只有外部显示调用的时候才会使用到loadByte方法
 * 由虚拟机调用时,仍然按照原有的双亲委派机制规则使用loadClass进行类加载
 * @author Administrator
 *
 */
public class HotSwapClassLoader extends ClassLoader{

	public HotSwapClassLoader() {
		super(HotSwapClassLoader.class.getClassLoader());
		
	}

	public Class loadByte(byte[] classByte){
		return defineClass(null, classByte, 0, classByte.length);
	}
	

}
