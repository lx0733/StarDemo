package com.lx.bale;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class TreadPool extends Pool{

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
	
	private Integer maxSize;
	private Integer coreSize;
	private Integer keepAliveTime;
	private LinkedBlockingQueue<Thread> queue;
	private List<Thread> excutePool;
	
	@Override
	protected void init() {
		//初始化队列与执行池
		this.queue = new LinkedBlockingQueue<Thread>();
		if (null == excutePool) {
			excutePool = new ArrayList<Thread>(coreSize);
		}
		//启动监控线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				checkAlive();
			}
		}).start();
	}
	
	public TreadPool(Integer maxSize, Integer coreSize, Integer keepAliveTime) {
		this.maxSize = maxSize;
		this.coreSize = coreSize;
		this.keepAliveTime = keepAliveTime;
	}
	
	//检查 每个线程是否执行完
	private void checkAlive(){
		
		while (true) {
			for (int i = 0; i < excutePool.size(); i++) {
				// 如果线程执行完毕,则移出执行池,从队列取出一个对象放入执行池 并start
				if (!excutePool.get(i).isAlive()) {
					
					excutePool.remove(i);
					Thread one = queue.poll();
					if (null != one) {
						excutePool.add(one);
						one.start();
					}
				}
				
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 执行  如果执行池未满直接加入执行池并start, 否则加入等待队列
	 * @param target
	 * @throws InterruptedException
	 */
	public void excute(Runnable target) throws InterruptedException {
		Thread thread = new Thread(target);
		int size = excutePool.size();
		if (size==maxSize) {
			queue.put(thread);
		}else {
			excutePool.add(thread);
			thread.start();
		}
	}

	
}
