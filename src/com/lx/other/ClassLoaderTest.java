package com.lx.other;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader classLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				try {
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					InputStream is = getClass().getResourceAsStream(fileName);
					if (null == is) {
						return super.loadClass(fileName);
					}
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (IOException e) {

					throw new ClassNotFoundException(name);
				}
			}
		};
		
		Object myClass = classLoader.loadClass("com.lx.other.ClassLoaderTest");
		
		System.out.println(myClass.getClass());
		System.out.println(myClass instanceof com.lx.other.ClassLoaderTest);
	}

}
