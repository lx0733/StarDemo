package com.lx.myNio;

import com.lx.myNio.businessService.HelloService;
import com.lx.myNio.proxy.RpcProxyFactory;

public class RpcNioConsumer {
    public static void main(String[] args) {
        multipartRpcNio();
    }

    /**
     * 多线程IO调用示例
     * 
     * @param
     * @return void
     * @throws BizException
     * @createTime：2018/7/1
     * @author: shakeli
     */
    public static void multipartRpcNio() {
        HelloService proxy = RpcProxyFactory.getMultService(HelloService.class);
        for (int i = 0; i < 100; i++) {
            final int j = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    String result = proxy.sayHello("world!");
                }
            };
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}

