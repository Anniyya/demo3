package com.example.demo3.mapper;

import com.example.demo3.entity.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @Select("select * from customer")
    public List<Customer> findAllCustomer();

    @Insert("insert into customer values (#{cid},#{cpwd},#{cname},#{csex},#{cbirth},#{cphone},#{cstatus})")
    public Integer insertCustomer(Customer customer);


    @Update("update customer set cid = #{cid}, cname = #{cname}, csex = #{csex}, cbirth = #{cbirth}, cphone = #{cphone}, cstatus = #{cstatus}"+
            "where cid = #{cid}")
    int updateCustomer(Customer customer);

    @Delete("delete from customer where cid = #{cid}")
    int deleteCustomer(Customer customer);

    // 批量删除
    @Delete({
            "<script>",
            "DELETE FROM customer WHERE cid IN ",
            "<foreach item='id' collection='list' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    void deleteCustomerById(@Param("list") List<String> ids);

    @Select("SELECT * FROM customer WHERE cstatus = '已通过' LIMIT #{pageNum}, #{pageSize}")
    List<Customer> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from customer")
    Integer selectTotal();

    @Select("SELECT * FROM customer WHERE cstatus = '已通过' AND cname LIKE CONCAT('%', #{cname}, '%') LIMIT #{offset}, #{pageSize}")
    List<Customer> selectPageByName(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("cname") String cname);

    @Select("SELECT count(*) FROM customer WHERE cstatus = '已通过' AND cname LIKE CONCAT('%', #{cname}, '%')")
    Integer selectTotalByName(@Param("cname") String cname);

    @Select("SELECT * FROM customer WHERE cstatus = '待审核' AND cname LIKE CONCAT('%', #{cname}, '%') LIMIT #{offset}, #{pageSize}")
    List<Customer> selectPageByName2(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("cname") String cname);

    @Select("SELECT count(*) FROM customer WHERE cstatus = '待审核' AND cname LIKE CONCAT('%', #{cname}, '%')")
    Integer selectTotalByName2(@Param("cname") String cname);

    //批量通过审核
    @Update({
            "<script>",
            "UPDATE customer",
            "SET cstatus = '已通过'",
            "WHERE cid IN",
            "<foreach item='id' collection='list' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    void checkCustomerById(List<String> ids);

    @Select("SELECT * FROM customer WHERE cid = #{cid} AND cpwd = #{cpwd} AND cstatus = '已通过'")//用户登录校验
    Customer findByCidAndCpwd(@Param("cid") String cid, @Param("cpwd") String cpwd);

    @Select("SELECT * FROM customer WHERE cid = #{cid}")//查找用户信息
    List<Customer> findCustomerByCid(@Param("cid") String cid);

    @Select("SELECT * FROM customer WHERE cid = #{cid}")
    Customer selectCustomerByCid(String cid);
}