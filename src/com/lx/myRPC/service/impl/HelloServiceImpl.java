package com.lx.myRPC.service.impl;

import com.lx.myRPC.service.HelloService;

/** 
 * HelloServiceImpl 
 *  
 * @author william.liangf 
 */  
public class HelloServiceImpl implements HelloService {  
  
    public String hello(String name) {  
        return "Hello " + name;  
    }  
  
}  