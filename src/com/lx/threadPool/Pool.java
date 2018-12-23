package com.lx.threadPool;

public interface Pool {
	void init(); 
	void execute(Runnable target)throws Exception;
}
