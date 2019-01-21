package com.lx.myRPC.provider;

import com.lx.myRPC.RpcFramework;
import com.lx.myRPC.service.HelloService;
import com.lx.myRPC.service.impl.HelloServiceImpl;

/** 
 * RpcProvider 
 *  
 * @author william.liangf 
 */  
public class RpcProvider {  
  
    public static void main(String[] args) throws Exception {  
        HelloService service = new HelloServiceImpl();  
        RpcFramework.export(service, 1234);  
    }  
  
}  