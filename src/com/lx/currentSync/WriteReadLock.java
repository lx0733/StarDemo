package com.lx.currentSync;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteReadLock {
   // private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true); //公平锁满足先进先取原则(FIFO)
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false); //非公平锁,默认也是非公平锁,随机从等待线程中抽取一个
    //private ReentrantLock lock1 = new ReentrantLock(false);也可以设置是否公平, 不分读写, 效果同上
    private static WriteReadLock ins;
    
    
    public static WriteReadLock getInstance(){
    	synchronized ("java") {
	    	if (null == ins) {
	    		ins = new WriteReadLock();
    			return ins;
			}else {
				return ins;
			}
    	}
    }
    
    public void read() {
        try {
        	System.out.println(lock.hashCode());
            lock.readLock().lock();
            System.out.println("获得读锁" + Thread.currentThread().getName() + "-" + System.currentTimeMillis());
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }
    public void write(){
        try {
        	System.out.println("读写锁的hashcode:"+lock.hashCode());
            lock.writeLock().lock();
            System.out.println("获得写锁" + Thread.currentThread().getName() + "-" + System.currentTimeMillis());
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
}