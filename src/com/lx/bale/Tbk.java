package com.lx.bale;

import java.io.File;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.swing.text.Segment;

import org.junit.Test;



public class Tbk implements Serializable{
	public Fibonacci f1 = new Fibonacci();
	
	
	
	public Fibonacci getF1(){
		return f1;
	}
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		int index = c.get(Calendar.HOUR_OF_DAY);
		System.out.println(index);
	}
	@Test
	public void fun() throws CloneNotSupportedException{

		System.out.println(2.0/0);
		
	}
	
	public synchronized void testSync(int i ) {
		System.out.println("输出"+i);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}