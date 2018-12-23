package com.lx.currentSync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionLock {
    private ReentrantLock lock = new ReentrantLock();
    public Condition conditionA = lock.newCondition();
    public Condition conditionB = lock.newCondition();
    
    private static ConditionLock ins;
    
    public static ConditionLock getInstance(){
    	synchronized ("java") {
	    	if (null == ins) {
	    		ins = new ConditionLock();
    			return ins;
			}else {
				return ins;
			}
    	}
    }
    
    public void methodA() {
        try {
            lock.lock();
            conditionA.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void methodB(){
        try {
            lock.lock();
            conditionB.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void signalAllA(){
        try {
            lock.lock();
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }
    public void signalAllB(){
        try {
            lock.lock();
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
