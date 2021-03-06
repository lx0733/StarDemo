package com.lx.myNio.provider;

import java.io.IOException;

import com.lx.myNio.businessService.HelloService;
import com.lx.myNio.businessService.impl.HelloServiceImpl;
import com.lx.myNio.common.BeanContainer;
import com.lx.myNio.nioService.RpcNioMultServer;

public class RpcNioProvider {
    public static void main(String[] args) throws IOException {
        // 将服务放进bean容器
        HelloService helloService = new HelloServiceImpl();
        BeanContainer.addBean(HelloService.class, helloService);
        // 启动NIO服务端
        startMultRpcNioServer();
    }

    public static void startMultRpcNioServer() {
    	//jdk8 函数式编程 用变量r引用一个函数
        Runnable r = () -> {
            try {
                RpcNioMultServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread t = new Thread(r);
        t.start();
    }
}

