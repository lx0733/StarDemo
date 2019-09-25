package com.lx.bale;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

public class Test01 {
	static int count = 0;
	@Test
	public void function() throws InterruptedException{
		/*BigDecimal b1 = new BigDecimal(2);
		BigDecimal b2 = new BigDecimal(0);
		BigDecimal b3 = new BigDecimal(3);
		BigDecimal scale = b1.multiply(b2).multiply(b3).setScale(3, BigDecimal.ROUND_HALF_UP);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String s = "2ddd";
		System.out.println(s.substring(0,1));
		System.out.println(((String)map.get("1")) instanceof String);
		
		
		
		final int a = 4;
		add(a);
		System.err.println(a);*/
		/*String lineStr = "cmdm.customer.info,";
		String key = lineStr.substring(0,lineStr.indexOf(","));
		String value = lineStr.substring(lineStr.indexOf(",")+1);
		//System.out.println(key);
		//System.out.println(value);
		System.out.println("http://cn.etowertech.com/".substring(7,23));*/
		   
	        for (int i = 0; i < 100000; i++) {
	            new Thread() {
	                public void run() {
	                    //for (int j = 0; j < 1000; j++) {
	                        count++;
	                    //}
	                }
	            }.start();
	        }
	        Thread.sleep(10000);
	        System.out.println("volatile count: " + count);

	}

	private void add(int a) {
		a++;
	}
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		LineNumberReader reader = null;
		InputStreamReader in = null;
	
			in = new InputStreamReader(new FileInputStream("D:/YKD/conf.txt"));
			reader = new LineNumberReader(in);
			int count = 1;
			
			while(true){
				String val = null;
				String lineStr = reader.readLine();
				if (lineStr == null || -1 == lineStr.indexOf(",")) {
					continue;
				}
				if (lineStr.indexOf(",") == lineStr.length()-1) {
					val = "";
				} else{
					 val = lineStr.substring(lineStr.indexOf(",")+1);
				}
				String key = lineStr.substring(0,lineStr.indexOf(","));
				if (count <= 600 && count > 345) {
					System.out.println("update sys_config set config_value = \""+val+"\" where config_code =\""+key+"\";");
				}
				count ++;
			}
			
			/*OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new OutputStream() {
				
				@Override
				public void write(int b) throws IOException {
					// TODO Auto-generated method stub
					
				}
			});
			outputStreamWriter.write(c);*/
	}
	@Test
	public void fun() throws InterruptedException{
		ThreadPool pool = new ThreadPool(8, 5, 2000);
		for (int i = 1; i <= 10; i++) {
			final int num = i;
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					System.out.println("线程:"+num+",执行开始");
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程:"+num+",执行结束");
				}
				
			};
			pool.excute(runnable);
		}
		
		Thread.sleep(500000);
	}
	@Test
	public void testFibo(){
		for (int i = 0; i < 10; i++) {
			System.out.println(Fibonacci.getNextValue());
		}
		Fibonacci.initValue();
		System.out.println(Fibonacci.getNextValue());
	}
	
	
}
