package com.example.demo3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //http://localhost:8077/hi
    //http://localhost:8077/hi?name=csy&age=18
    @GetMapping("/hi")
    public String hello(String name,String age){
        return "你好" + name + age;
    }

    @RequestMapping(value = "/posttest1",method = RequestMethod.POST)
    public String postTest1(){ return "POST请求"; }

    @RequestMapping(value = "/posttest2",method = RequestMethod.POST)
    public String postTest2(String username,String password){
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        return "POST请求";
    }
}
