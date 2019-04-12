package com.lx.classLoader;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
 
public class Portal {
	private static Map<ClassLoader, String> childClassLoaders = new WeakHashMap<ClassLoader, String>();
	public String[] findReloadedContextMemoryLeaks() {
	        System.gc();
	        List<String> result = new ArrayList<String>();
	        for (Map.Entry<ClassLoader, String> entry : childClassLoaders.entrySet()) {
	            ClassLoader cl = entry.getKey();
			/*
			 * if (!((DynamicClassLoader) cl).isStarted()) { result.add(entry.getValue()); }
			 */
	        }
	        return result.toArray(new String[result.size()]);
	    }
	
    public static void main(String[] args) throws InterruptedException {
    	List<DynamicClassLoader> list = new ArrayList<DynamicClassLoader>();
    	List<Object> ins = new ArrayList<Object>();
        DynamicDom dmo = new DynamicDom();//xml文件解析类
        Map<String, List<String>> classes;
        //重载ClassLoader类
        for (int i = 0; i < 5; i++) {
			
        	DynamicClassLoader loader = new DynamicClassLoader(new String[]{"D:\\tmp\\"}); 
        	list.add(loader);
	        try {
	            classes = dmo.getMethods("D:\\tmp\\testCL.xml");
	            for(String key:classes.keySet()){ 
	                for(String clazz : classes.get(key)){ 
	                    Class<?> c =loader.findClass(key);//类名字
	                    Object instance = c.newInstance();
	                    ins.add(instance);
	                    c.getMethod(clazz).invoke(instance);//方法名字
	                }
	            } 
	        }catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
        }
        System.out.println("类加载完毕");
        Thread.sleep(10000);
        System.out.println("消除引用");
        list.remove(2);
        ins.remove(2);
        System.gc();
        System.out.println("可以开始dump");
        Thread.sleep(5000);
    }
}