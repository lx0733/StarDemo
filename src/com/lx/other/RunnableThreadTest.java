package com.lx.other;

import java.util.concurrent.atomic.AtomicInteger;

public class RunnableThreadTest implements Runnable {

	private int i ;

	public void run() {
		for (; i<100; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			if (i == 20) {
				RunnableThreadTest rtt = new RunnableThreadTest();
				new Thread(rtt, "线程1").start();
				new Thread(rtt, "线程2").start();
			}
		}

	}

}