package com.example.demo3.controller;

import com.example.demo3.entity.User;
import com.example.demo3.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    //自动注入实例化对象，springboot的功能
    private UserMapper userMapper;

    @GetMapping("/user")
    public List query(){

        List<User> list = userMapper.find();
        System.out.println(list);
        return list;//自动转换为Jason格式传给前端
    }

    @PostMapping("/user2")
    public String save(User user){
        int i = userMapper.insert(user);
        if (i>0){
            return "成功";
        }else {
            return "失败";
        }
    }
}
