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

    @Select("select count(*) from customer")
    Integer selectTotal();

    @Select("SELECT * FROM customer WHERE cname LIKE #{cname} LIMIT #{offset}, #{pageSize}")
    List<Customer> selectPageByName(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("cname") String cname);

    @Select("SELECT count(*) FROM customer WHERE cname LIKE #{cname}")
    Integer selectTotalByName(@Param("cname") String cname);
}
