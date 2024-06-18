package com.example.demo3.controller;

import com.example.demo3.entity.Customer;
import com.example.demo3.mapper.CustomerMapper;
import com.example.demo3.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {
    @Autowired
    //自动注入实例化对象，springboot的功能
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/insertcustomer")//增加和修改
    public String save(Customer customer){
        int i = customerService.save(customer);
        if (i>0){
            return "成功";
        }else {
            return "失败";
        }
    }

    @GetMapping("/findcustomer")//查找所有顾客信息
    public List query(){
        List<Customer> list = customerMapper.findAllCustomer();
        return list;//自动转换为Jason格式传给前端
    }

    @GetMapping("/deletecustomer")//根据顾客id，删除对应的顾客信息
    public int delete(Customer customer){
        if(customer.getCid() == null){
            return 2;
        }else {
            return customerMapper.deleteCustomer(customer);
        }
    }

    @GetMapping("/page")//分页查询顾客信息
    public List<Customer> findPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        System.out.println("Offset: " + offset);
        System.out.println("Page Size: " + pageSize);
        return customerMapper.selectPage(offset, pageSize);
    }
}
