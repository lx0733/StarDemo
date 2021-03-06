package com.lx.currentSync;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;



public class TestFunction{
	
	static ReentrantLock lock = new ReentrantLock();
	/**
	 *  测试ReentrantReadWriteLock 读写锁, 读读共享、写写互斥、读写互斥。
	 *  区别: 
	 *  1.ReentrantLock和synchronized关键字最大的区别是 sync的锁只能是非公平的, 而ReentrantLock可选择,效率方面sync略快
	 *  2.ReentrantLock的粒度更细, 提供了condition类进行分组唤醒需要唤醒的线程们
	 *  3.ReenTrantLock提供了一种能够中断等待锁的线程的机制，通过lock.lockInterruptibly()来实现这个机制
	 *  如果你需要使用以上三个特性则用这个类
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					WriteReadLock wrl1 = WriteReadLock.getInstance();
					System.out.println(wrl1.hashCode());
					wrl1.write();
					wrl1.read();
				}
			}).start();
		}
	}
	
	/**
	 * 可以分类实现和wait和notify的等待/通知效果
	 * 
	 * wait()和notify()是Object的方法，当多个线程中多个不同的方法调用wait()时，当新的线程调用notifyAll()时会唤醒所wait线程，
	 * 但是当我们只想唤醒执行某一类方法的线程时，例如只想唤醒执行上述代码的methodA()方法的线程时，该怎么办呢？
	 * 
	 * 这时，我们的Condition类就派上用场了，一个Lock对象里可以创建多个Condition实例（即对象监视器），
	 * 那么线程就可以注册在指定的Condition中，从而当多个线程进入wait时，可以更加精确的有选择性的唤醒某些线程。
	 * @throws InterruptedException
	 */
	@Test
	public void fun01() throws InterruptedException{
		ConditionLock ins = ConditionLock.getInstance();
		for (int i = 0; i < 6; i++) {
			int num = i;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
				if (num % 2 == 0) {
					System.out.println("线程"+num+"准备执行A");
					ins.methodA();
					System.out.println("线程"+num+"唤醒,执行完毕A");
				}else{
					System.out.println("线程"+num+"准备执行B");
					ins.methodB();
					System.out.println("线程"+num+"唤醒,执行完毕B");
				}
					
				}
			}).start();
			
		}
		
		Thread.sleep(2000);
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					ins.signalAllA();
				}
			}).start();
		}
		Thread.sleep(5000);
		ins.signalAllB();
	}
	/**
	 * ReentrantLock的可重入效果，加锁两次必须要解锁两次才可以
	 * @throws InterruptedException
	 */
	@Test
	public void fun02() throws InterruptedException {
		ThreadGroup group = new ThreadGroup("group1");
		for (int i = 0; i < 5; i++) {
			final int count = i;
			new Thread(group,new Runnable() {
				@Override
				public void run() {
					lock.lock();
					System.out.println(count+"第一层进来了");
					/*
					 * lock.lock(); System.out.println(count+"第二层进来了");
					 */
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lock.unlock();
					System.out.println(count+"第一层解锁");
				}
			}).start();
		}
		int c = 0;
		while (c<500) {
			group.list();
			//System.out.println(group.activeCount());
			Thread.sleep(200);
			c ++;
		}
		Thread.sleep(500000);
	}
	@Test
	public void fun03() throws InterruptedException {
		 ThreadGroup group = new ThreadGroup("TestGroup");
	        new Thread(group, () -> {
	            while(true) {
	                try {
	                    TimeUnit.MILLISECONDS.sleep(2);
	                } catch (InterruptedException e) {
	                    //received interrupt signal and clear quickly
	                    System.out.println(Thread.currentThread().isInterrupted());
	                    break;
	                }
	            }
	            System.out.println("t1 will exit");
	        }, "t1").start();
	        new Thread(group, () -> {
	            while(true) {
	                try {
	                    TimeUnit.MILLISECONDS.sleep(2);
	                } catch (InterruptedException e) {
	                    //received interrupt signal and clear quickly
	                    System.out.println(Thread.currentThread().isInterrupted());
	                    break;
	                }
	            }
	            System.out.println("t2 will exit");
	        }, "t2").start();
	        //make sure all threads start
	        TimeUnit.MILLISECONDS.sleep(2);
	        group.interrupt();
	}
	
	
	@Test
	public void fun04() throws InterruptedException {
		while (true) {
			System.out.println("sss");
			TimeUnit.SECONDS.sleep(1);
		}
	}
	
	/**
	 * ThreadGroup里面的线程运行中创建的线程也会归属到此ThreadGroup下
	 */
	@Test
	public void testThreadGroup() throws InterruptedException{
		ThreadGroup group = new ThreadGroup("group1");
		for (int i = 0; i < 5; i++) {
			final int count = i;
			Thread thread = new Thread(group,new Runnable() {
				@Override
				public void run() {
					try {
						Thread thread2 = new Thread(new Runnable() {
							
							@Override
							public void run() {
								try {
									Thread.sleep(30000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						thread2.setName("内部线程");
						thread2.start();
						Thread.sleep(50000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thread.setName("自建线程");
			thread.start();
		}
		int c = 0;
		while (c<500) {
			group.list();
			System.err.println(group.activeCount());
			Thread.sleep(200);
			c ++;
		}
		Thread.sleep(500000);
	}
	
	@Test
	public void testTerminate() throws InterruptedException{
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 1; i < 6; i++) {
			final int count = i;
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(count*1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thread.setName("自建线程-"+count);
			thread.start();
			threads.add(thread);
		}
		int c = 0;
		while (c<500) {
			Iterator<Thread> iterator = threads.iterator();
			while (iterator.hasNext()) {
				Thread thread = (Thread) iterator.next();
				boolean alive = thread.isAlive();
				if (!alive) {
					iterator.remove();
				}
				System.out.print(thread.getName()+"-"+alive+"  ");
				
			}
			
			System.out.println();
			System.out.println();
			
			Thread.sleep(1000);
			c ++;
		}
		Thread.sleep(500000);
	}
	
}
