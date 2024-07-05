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
            // 查询是否存在该 cid
            Customer existingCustomer = customerMapper.selectCustomerByCid(customer.getCid());
            if (existingCustomer != null) {
                // 更新操作
                return customerMapper.updateCustomer(customer);
            } else {
                // 插入操作
                return customerMapper.insertCustomer(customer);
            }
        }else {
            // 如果 cid 为空，直接插入新记录
            throw new IllegalArgumentException("cid为空");
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
