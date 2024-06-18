package com.example.demo3.controller;

import com.example.demo3.entity.Customer;
import com.example.demo3.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {
    @Autowired
    //自动注入实例化对象，springboot的功能
    private CustomerMapper customerMapper;

    @PostMapping("/insertcustomer")
    public Integer save(@RequestBody Customer customer){
        return customerMapper.insertCustomer(customer);
    }

    @GetMapping("/findcustomer")
    public List query(){
        List<Customer> list = customerMapper.findAllCustomer();
        return list;//自动转换为Jason格式传给前端
    }
}
