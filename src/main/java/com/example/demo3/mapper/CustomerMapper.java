package com.example.demo3.mapper;

import com.example.demo3.entity.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @Select("select * from customer")
    public List<Customer> findAllCustomer();

    @Insert("insert into customer values (#{cid},#{cpwd},#{cname},#{csex},#{cbirth},#{cphone})")
    public Integer insertCustomer(Customer customer);


    @Update("update customer set cid = #{cid}, cname = #{cname}, csex = #{csex}, cbirth = #{cbirth}, cphone = #{cphone}"+
            "where cid = #{cid}")
    int update(Customer customer);

    @Delete("delete from customer where cid = #{cid}")
    int deleteCustomer(Customer customer);

    @Select("select * from customer limit #{pageNum},#{pageSize}")
    List<Customer> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}
