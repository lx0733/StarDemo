package com.lx.other;

import com.sun.org.apache.xpath.internal.operations.Mult;

public class Texst {
	public static void main(String[] args) throws InterruptedException {
		TestClass t1 = new TestClass();
		TestClass t2 = new TestClass();
		System.out.println(t1.getStaticClass().hashCode()+"--"+t2.getStaticClass().hashCode());
		System.out.println(t1.getString().hashCode()+"--"+t2.getString().hashCode());
		System.out.println("线程启动");
		
		MyThread mt = MyThread.getInstance();
		
		mt.start();
		Thread.sleep(4000);
		
		MyThread mt2 = MyThread.getInstance();
		mt2.getState();
		mt2.start();
		
		System.out.println("同一个对象吗"+ (mt ==mt2));
		Thread.sleep(12000);
	}
}

class MyThread extends Thread{
	private static MyThread myThread;
	
	
	private static Object lock = new Object();
	
	private MyThread() {
		super();
	}

	@Override
	public void run() {
		System.out.println("thread excuted");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("thread awaken");
	}
	
	public static MyThread getInstance(){
		if (null == myThread) {
			synchronized (lock) {
				myThread = new MyThread();
				return myThread;
			}
		}else {
			return myThread;
		}
	}
}