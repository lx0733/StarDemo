package com.lx.currentSync;

import java.util.concurrent.locks.Condition;

import org.junit.Test;



public class TestFunction {
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
				if (num / 2 == 0) {
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
		ins.signalAllA();
		Thread.sleep(5000);
		ins.signalAllB();
	}
}
