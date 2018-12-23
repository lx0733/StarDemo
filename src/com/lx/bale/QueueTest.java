package com.lx.bale;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

public class QueueTest {
	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<Object> queue = new LinkedBlockingQueue<Object>(3);
		queue.add("11");
		queue.add("22");
		queue.add("33");
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(queue.poll());
			}
		}).start();
		queue.put("44");
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());

	}
	
	@Test
	public void fun01() throws InterruptedException{
		/**
		 * ArrayBlockingQueue 等待时间长的线程优先加入,但LinkedBlokingQueue貌似也是一样 等待长的先加入
		 */
		final BlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(1);
		queue.offer("baseData");
		
		//线程1
		final Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				int count = 0;
				while (true) {
					try {
						Thread.sleep(1000);
						count ++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (count > 8) {
						//System.out.println("跳出循环");
						break;
					}
				}
			}
		});
		t1.start();
		// 启动线程2
		final Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				int count = 0;
				while (true) {
					try {
						Thread.sleep(1000);
						count ++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (count > 8) {
						//System.out.println("跳出循环");
						break;
					}
				}
			}
		});
		t2.start();
		
		
		//插入t1 阻塞
		Thread t1Insert = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t1插入阻塞");
					queue.put(t1);
					System.out.println("t1插入成功");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1Insert.start();
		Thread.sleep(1000);
		//插入t2 阻塞
		Thread t2Insert = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t2插入阻塞");
					queue.put(t2);
					System.out.println("t2插入成功");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t2Insert.start();
		
		
		
		Thread.sleep(3000);
		System.out.println("取出原始对象"+queue.poll());
		
		/*while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (t1.isAlive()) {
				System.out.println("线程存活");
			}else{
				System.out.println("线程完毕");
			}
		}*/
		
	}
	@Test
	public void fun02(){
		PriorityBlockingQueue<Object> queue = new PriorityBlockingQueue<Object>();
		queue.put(new String());
	}
}
