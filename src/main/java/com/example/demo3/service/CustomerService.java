package com.example.demo3.service;

import com.example.demo3.entity.Customer;
import com.example.demo3.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public int save(Customer customer) {
        if (customer.getCid() != null) {
            // 更新操作
            return customerMapper.updateCustomer(customer);
        } else {
            // 新增操作
            return customerMapper.insertCustomer(customer);
        }
    }

    // 批量删除
    public int deleteCustomerById(List<String> ids) {
        customerMapper.deleteCustomerById(ids); // 调用 Mapper 执行批量删除
        return 1; // 无论删除多少条记录，始终返回1表示删除成功
    }

    public int checkCustomerById(List<String> ids) {
        customerMapper.checkCustomerById(ids); // 调用 Mapper 执行批量通过
        return 1;
    }
}
