package com.lx.newjdk;
 
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
 
@FunctionalInterface  // 新特性2 函数式接口 只允许一个抽象方法
public interface Demo {
 
	public void oldMethod(String la);
 
	 public default void test2(){  // 新特性1 接口内的default方法 ,可定义方法体
	        System.out.println("我是新特性1");
    }
	 
	 public static void main(String[] args) {
		/*new Demo() {
			@Override
			public void oldMethod() {
				System.out.println("老方法");
			}
		}.oldMethod();*/
		 /**
		  * lambda 1
		  */
		 Demo d = la -> System.out.println("真牛皮"+la);  // 新特性3 lambda表达式
		// Demo d = (la) -> System.out.println("真牛皮"+la);  // 参数可以用()括号括起来, 当只有一个参数时可以去掉括号;
		 d.oldMethod("搞一下");
		 /**
		  * lambda 2
		  */
		Runnable r1 = () -> System.out.println("线程开始");   // 无参数时必须加上括号
		//new Thread(r1).start();
		new Thread(() -> System.out.println("这么屌的吗,直接一个括号????")).start();
		
		/**
		 * lambda 3 不带参数
		 */
		List<String> list =Arrays.asList("aaa","fsa","ser","eere");
        Collections.sort(list, (a,b)-> Integer.parseInt(String.valueOf(b.compareTo(a)+1)));
        for (String string : list) {
            System.out.println(string);
        }
        
        /**
         * lambda 4 带参数
         */
        List<String> list2 =Arrays.asList("aaa","fsa","ser","eere");
        //List<Object> list2 =Arrays.asList("aaa","fsa","ser","eere"); 错误 list泛型需要与后面的comparator泛型相同
        Collections.sort(list2, (Comparator<? super String>) (String a,String b)->{
            return b.compareTo(a);
        }
        );
        //也可以这么写
       /* Collections.sort(list2, (String a,String b)->{
            return b.compareTo(a);
        }
        );*/ 
        
        
        
        for (String string : list) {
            System.out.println(string);
        }
        
        /**
         * lambda 5 允许使用外部变量(自动且隐性的给外部变量用final修饰),相当于内部类
         */
        int count =0;
        Jdk8Interface j8 = (s1,s2) -> System.out.println(s1+s2+count);
        j8.method("参数1", "参数2");
		
		new Jdk8Interface() {
			
			@Override
			public void method(String s1, String s2) {
				System.out.println(s1+s2+count);
				//count = 5; 会报错 证明是final的
			}
		}.method("参数1", "参数2");;
 
	}
	 
	
}
