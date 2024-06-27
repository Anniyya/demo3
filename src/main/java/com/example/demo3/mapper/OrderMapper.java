package com.example.demo3.mapper;

import com.example.demo3.entity.Customer;
import com.example.demo3.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM orderform WHERE ostatus = '已支付' AND createtime LIKE CONCAT('%', #{createtime}, '%') LIMIT #{offset}, #{pageSize}")
    List<Order> selectPageByCreatetime(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("createtime") String createtime);

    @Select("SELECT count(*) FROM orderform WHERE ostatus = '已支付' AND createtime LIKE CONCAT('%', #{createtime}, '%')")
    Integer selectTotalByCreatetime(@Param("createtime") String createtime);

    @Select("SELECT * \n" +
            "FROM orderform \n" +
            "WHERE (ostatus = '已完成' OR ostatus = '已取消') \n" +
            "  AND overtime LIKE CONCAT('%', #{overtime}, '%') \n" +
            "LIMIT #{offset}, #{pageSize};\n")
    List<Order> selectPageByOvertime(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("overtime") String overtime);

    @Select("SELECT COUNT(*) \n" +
            "FROM orderform \n" +
            "WHERE (ostatus = '已完成' OR ostatus = '已取消') \n" +
            "  AND overtime LIKE CONCAT('%', #{overtime}, '%');\n")
    Integer selectTotalByOvertime(@Param("overtime") String overtime);

    @Update("update orderform set ostatus = #{ostatus} where oid = #{oid}")
    int updateOrder(Order order);

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

    @Select("SELECT COALESCE(COUNT(orderform.oid), 0) " +
            "FROM (" +
            "   SELECT DATE(createtime) AS createtime " +
            "   FROM orderform " +
            "   WHERE createtime BETWEEN DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY) " +
            "       AND DATE_ADD(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL 7 DAY) " +
            "   GROUP BY DATE(createtime)" +
            ") AS subq " +
            "LEFT JOIN orderform ON DATE(orderform.createtime) = subq.createtime AND orderform.ostatus = '已完成' " +
            "GROUP BY subq.createtime " +
            "ORDER BY subq.createtime")
    List<Integer> getWeeklyCompletedOrders();

    @Select("SELECT * FROM orderform WHERE cid = #{cid}")
    List<Order> selectOrderByCid(String cid);

    @Select("SELECT * FROM orderform WHERE oid = #{oid}")
    Order selectOrderByOid(String oid);

}
