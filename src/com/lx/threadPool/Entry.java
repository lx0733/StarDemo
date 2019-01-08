package com.lx.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import org.junit.Test;

public class Entry {
	public static Object o1 = new Object();
	public static Object o2 = new Object();

	/**
	 * 自定义线程池测试方法
	 * 
	 * @throws Exception
	 */
	@Test
	public void fun() throws Exception {
		TreadPool pool = new TreadPool(8, 5, 2, 2000);
		for (int i = 1; i <= 10; i++) {
			final int num = i;
			pool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程:" + num + ",执行开始");
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程:" + num + ",执行结束");
				}

			});

		}

		Thread.sleep(500000);
	}

	/**
	 * 关于join
	 * 
	 * @param args
	 * @throws InterruptedException
	 * 
	 */
	public static void main(String[] args) throws InterruptedException {
		List<Thread> list = new ArrayList<Thread>();
		for (int i = 0; i < 3; i++) {
			final int num = i;
			Thread son = new Thread(new Runnable() {
				public void run() {
					System.out.println("线程" + num + "开始");
					try {
						if (num == 2) {
							Thread.sleep(60000);
						} else {
							Thread.sleep(20000);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程" + num + "结束");
				}
			});
			son.start();
			// son.join();
			list.add(son);
		}
		// Thread.yield(); //放弃持有的monitor 进入等待池和其他线程一起竞争,
		// 有可能再次获得monitor也有可能是别的线程获得
		for (Thread thread : list) {
			// thread.join(1000);
			thread.join(); // join() = join(0) ,当参数为0时,
							// 如果thread线程仍在活动中isAlive(), 则调用线程无限休眠wait(0) ,为什么?
							// 可能是thread结束后调用了notify方法
			System.out.println("加入");
		}
		System.out.println("主线程开始运行");

	}

	/**
	 * 关于notify 和 notifyAll
	 *
	 */
	@Test
	public void fun02() throws InterruptedException {
		/**
		 * 调用wait()，notify()和notifyAll()的线程在调用这些方法前必须"拥有"对象的锁。
		 * 当前的线程不是此对象锁的所有者，却调用该对象的notify()，notify()，wait()方法时抛出该异常。
		 * 即 如果不在同步块中调用wait()方法则抛出 illegalMonitorStateException
		 */
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					synchronized (o1) {

						System.out.println("开始休眠11");
						// 大约已经到达指定的实际时间。但是，如果 timeout
						// 为零，则不考虑实际时间，在获得通知前该线程将一直等待。
						o1.wait(0);// 相当于o1.wait(0);
						System.out.println("唤醒11");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					synchronized (o1) {

						System.out.println("开始休眠22");
						o1.wait(1000);
						System.out.println("唤醒22");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread1.start();
		thread2.start();
		Thread.sleep(3000);
		synchronized (o1) {
			// 必须用这个对象监视器 monitor 去调用notify,
			// 否则报错java.lang.IllegalMonitorStateException
			System.out.println("调用");
			o1.notify();// 随机唤醒一个, 其余的等待
			o1.notifyAll();// 唤醒等待池的所有线程, 随机一个线程拿到monitor(即对象监视器, 此处是o1,
							// 如果用o2则没用)
			System.out.println("完毕");
		}
		Thread.sleep(3000);

	}

	@Test
	public void fun03() throws InterruptedException {
		System.out.println("测试: 是否调用线程进行等待");
		JoinTools jt = new JoinTools();
		jt.startWait(3000);
		System.out.println("调用线程被等待结束");
	}

	@Test
	public void fun04() throws InterruptedException {
		OtherThreadPool pool = new OtherThreadPool(5);
		for (int i = 1; i <= 12; i++) {
			final int num = i;
			pool.exec(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程" + num + "执行");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程" + num + "finish");
				}
			});
		}
		Thread.sleep(50000);
	}
/**
 * CountDownLatch 线程计数阻塞, 不可复用,一旦用完就抛弃掉吧,由主线程调用await并阻塞,直到计数被子线程将计数置为零,主线程继续执行
 * @throws InterruptedException
 */
	@Test
	public void fun05() throws InterruptedException {
		final CountDownLatch cdl = new CountDownLatch(3);
		for (int i = 0; i < 5; i++) {

			Runnable runnable = new Runnable() {
				public void run() {
					System.out.println("-----start-----");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("-----end-----");
					cdl.countDown();

				}
			};
			new Thread(runnable).start();
		}
		System.out.println("主线程开始等待");
		cdl.await();
		System.out.println("主线程等待完毕");
	}
/**
 * cyclicBarrier 等待用对象, 构造里传个i, 直到有i个线程(子线程)调用await方法才继续执行,否则阻塞. 也可以给个最大等待时间
 *               与count
 * @throws InterruptedException
 */
	@Test
	public void fun06() throws InterruptedException {

		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N);
		for (int i = 0; i < N; i++) {
			new Writer(barrier,i).start();
		}
		
		
		Thread.sleep(20000);
	}

	static class Writer extends Thread {
		private CyclicBarrier cyclicBarrier;
		private Integer i;
		
		public Writer(CyclicBarrier cyclicBarrier,Integer i) {
			this.cyclicBarrier = cyclicBarrier;
			this.i = i;
		}

		@Override
		public void run() {
			System.out.println("线程" + Thread.currentThread().getName()
					+ "正在写入数据...");
			try {
				Thread.sleep(i*1500); // 以睡眠来模拟写入数据操作
				System.out.println("线程" + Thread.currentThread().getName()
						+ "写入数据完毕，等待其他线程写入完毕");
				cyclicBarrier.await();//等待点调用了才堵塞
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("所有线程等待完毕，继续处理其他任务...");
		}
	}
	
	/**
	 * 感觉用处不大, 可以用来锁住局部代码, 比synchronize代码快好的地方是可以给多个锁(semaphore的许可) 
	 * @throws InterruptedException
	 */
	@Test
	public void fun07() throws InterruptedException{
        int N = 8;            //工人数
        Semaphore semaphore = new Semaphore(5); //机器数目
        for(int i=0;i<N;i++)
            new Worker(i,semaphore).start();
        Thread.sleep(20000);
    }
     
    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }
         
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(3000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();           
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
	 * 测试Thread类中的过期方法 suspend 和resume
	 * 
	 */
	@Test
	public void fun08(){
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			final int num = i;
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程" + num);
					Thread.currentThread().suspend();
					if (num == 2) {
						System.out.println("线程" + num +"休眠");
						
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//Thread.
					}
					System.out.println("线程" + num + "被唤醒");
				}
			});
			threads.add(thread);
			thread.start();
			thread.setName("线程"+num);
		}
		try {
			Thread.sleep(5000);
			threads.get(2).resume();
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("主线程运行");
		
	}
	@Test
	public void fun09() throws InterruptedException{
		o1.wait();
		System.out.println("asdsad");
	}
	
}



class JoinTools {
	public final synchronized void startWait(long millis)
			throws InterruptedException {
		wait(millis);
	}
}
