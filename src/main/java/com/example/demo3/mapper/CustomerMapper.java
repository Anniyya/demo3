package com.example.demo3.mapper;

import com.example.demo3.entity.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @Select("select * from customer")
    public List<Customer> findAllCustomer();

    @Insert("insert into customer(cid,cpwd,cname,csex,cbirth,cphone) values (#{cid},#{cpwd}),#{cname},#{csex},#{cbirth},#{cphone})")
    Integer insertCustomer(Customer customer);
}
