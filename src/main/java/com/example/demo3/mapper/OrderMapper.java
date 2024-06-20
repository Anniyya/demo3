package com.example.demo3.mapper;

import com.example.demo3.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM orderform WHERE ostatus = '处理中' AND createtime LIKE CONCAT('%', #{createtime}, '%') LIMIT #{offset}, #{pageSize}")
    List<Order> selectPageByCreatetime(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("createtime") String createtime);

    @Select("SELECT count(*) FROM orderform WHERE ostatus = '处理中' AND createtime LIKE CONCAT('%', #{createtime}, '%')")
    Integer selectTotalByCreatetime(@Param("createtime") String createtime);

    @Select("SELECT * FROM orderform WHERE ostatus = '已完成' or ostatus='已取消' AND overtime LIKE CONCAT('%', #{overtime}, '%') LIMIT #{offset}, #{pageSize}")
    List<Order> selectPageByOvertime(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("overtime") String overtime);

    @Select("SELECT count(*) FROM orderform WHERE ostatus = '已完成' or ostatus='已取消' AND overtime LIKE CONCAT('%', #{overtime}, '%')")
    Integer selectTotalByOvertime(@Param("overtime") String overtime);

    @Update("update orderform set ostatus = #{ostatus} where oid = #{oid}")
    int overOrder(Order order);

    @Update({
            "<script>",
            "UPDATE orderform",
            "SET ostatus = '已通过'",
            "WHERE oid IN",
            "<foreach item='id' collection='list' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    int overSelectOrder(List<String> ids);

    @Update("update orderform set ocontent = #{ocontent}, overtime = #{overtime}, ostatus = #{ostatus}, remark = #{remark}, sum = #{sum}"+
            "where oid = #{oid}")
    int editOrder(Order order);

    @Delete("delete from orderform where oid = #{oid}")
    int deleteOrder(Order order);

    @Delete({
            "<script>",
            "DELETE FROM orderform WHERE oid IN ",
            "<foreach item='id' collection='list' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    int deleteOrdersByOid(List<String> ids);
}
