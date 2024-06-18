package com.example.demo3.service;

import com.example.demo3.entity.Customer;
import com.example.demo3.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public int save(Customer customer){
        if (customer.getCid() == null){  //没有顾客id表示新增
            return customerMapper.insertCustomer(customer);
        }else { //否则为更新
            return customerMapper.update(customer);
        }
    }
}
