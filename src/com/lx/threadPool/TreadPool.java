package com.lx.threadPool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class TreadPool implements Pool{

	/*
	1. maxSize 
	线程池允许创建的最大线程数。当workQueue使用无界队列时（如LinkBlockingQueue），则此参数无效。
	它与corePoolSize的作用是调整“线程池中实际运行的线程的数量”。
	例如，当新任务提交给线程池时，如果线程池中运行的线程数量小于corePoolSize，则创建新线城来处理请求；
	如果此时线程池中运行的线程数量大于corePoolSize但是却小于maximumPoolSize，则仅当阻塞队列（workQueue）满时才创建新线程。
	如果设置的corePoolSize等于maximumPoolSize则创建了固定大小的线程池。
	如果将maximumPoolSize设置为基本的无界值（如Integer.MAX_VALUE），则允许线程池适应任意数量的并发任务

	2. excute 启动
	3. coreSize 核心线程数
	4. keepAliveTime 当前线程池线程总数大于核心线程时，终止多余的空闲线程的时间
	*/
	
	// 最大线程数
	private Integer maxSize;
	// 核心线程数
	private Integer coreSize;
	// 空闲线程存活时间
	private long keepAliveTime;
	// 队列最大长度
	private int queueCapacity;
	private LinkedBlockingQueue<Runnable> queue;
	private List<Runnable> excutePool;
	
	private Object lock = new Object();
	@Override
	public void init() {
		//初始化队列与执行池
		this.queue = new LinkedBlockingQueue<Runnable>(queueCapacity);
		if (null == excutePool) {
			excutePool = new ArrayList<Runnable>(coreSize);
		}
		//启动监控线程
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				checkAlive();
			}
		}).start();*/
	}
	
	public TreadPool(Integer maxSize, Integer coreSize, long keepAliveTime, Integer queueCapacity) {
		this.maxSize = maxSize;
		this.coreSize = coreSize;
		this.keepAliveTime = keepAliveTime;
		this.queueCapacity = queueCapacity;
		init();
	}
	
	/**
	 * 这是我原来的想法 ,另开一个监控线程去取任务和让等待队列的线程开始执行.
	 * 不能这么做, 
	 * 应该是工作线程自己取任务,无限执行,如果取不到则阻塞(queue.take())
	 */
	//检查 每个线程是否执行完
	/*private void checkAlive(){
		Iterator<Runnable> it = excutePool.iterator();
		while (true) {
			while (it.hasNext()) {
				synchronized (lock) {
					// 如果线程执行完毕,则移出执行池,从队列取出一个对象放入执行池 并start
					if (!it.next().isAlive()) {
						it.remove();
						Thread one ;
						try {
							one = queue.take();
							//one.
						} catch (InterruptedException e) {
							System.err.println("take element exception occurred");
							e.printStackTrace();
							continue;
						}
						if (null != one) {
							excutePool.add(one);
							one.start();
						}
					}
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}*/
	/**
	 * 执行  如果执行池未满直接加入执行池并start, 否则加入等待队列
	 * @param target
	 * @throws Exception 
	 * @throws InterruptedException
	 */
	@Override
	public void execute(Runnable target) throws Exception {
		//工作线程小于设置的核心线程, 则开启一个工作线程worker并start,循环取队列中的任务
		if (excutePool.size() < coreSize) {
			Worker worker = new Worker(target);
			Thread thread = new Thread(worker);
			excutePool.add(worker);
			thread.start();
		//如果工作线程大于核心线程,则直接放入队列
		}else{
			boolean isFull = queue.offer(target);
			if (!isFull) {
				throw new RuntimeException("Queue Rejection Exception: queue is full");
			}
		}
	}
	
	class Worker implements Runnable{

		private Worker(Runnable task) {
			boolean isFull = queue.offer(task);
			if (!isFull) {
				throw new RuntimeException("Queue Rejection Exception: queue is full");
			}
		}

		@Override
		public void run() {
			while (true ) {
				try {
					//这里要用run方法(也可以让任务开始执行,不过是同步的相当于没开线程,而start是异步的), 
					//如果用thread包装一下再start,会在工作线程外再开一个线程, 达不到管理和控制线程数的目的了
					Runnable task = queue.take();
					task.run();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

	}
}
