package com.lx.myNio.businessService.impl;

import com.lx.myNio.businessService.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
