package com.example.demo3.controller;

import com.example.demo3.entity.Customer;
import com.example.demo3.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping("/customerlogin")
    public int customerLogin(@RequestBody Customer customer) {
        Customer loginCustomer = customerMapper.findByCidAndCpwd(customer.getCid(), customer.getCpwd());
        if (loginCustomer != null) {
            return 1; // 登录成功
        } else {
            return 2; // 用户名或密码错误
        }
    }


}
