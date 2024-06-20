package com.example.demo3.controller;

import com.example.demo3.entity.Customer;
import com.example.demo3.entity.Order;
import com.example.demo3.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    //自动注入实例化对象，springboot的功能
    private OrderMapper orderMapper;

    @GetMapping("/selectbycreatetime") //按创建日期模糊查询订单信息
    public Map<String, Object> findPage(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("createtime") String createtime) {
        int offset = (pageNum - 1) * pageSize;
        String createtime2 = "%" + createtime + "%";
        List<Order> data = orderMapper.selectPageByCreatetime(offset, pageSize, createtime2);
        Integer total = orderMapper.selectTotalByCreatetime(createtime2);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    @GetMapping("/selectbyovertime") //按创建日期模糊查询订单信息
    public Map<String, Object> findPage2(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("overtime") String overtime) {
        int offset = (pageNum - 1) * pageSize;
        String overtime2 = "%" + overtime + "%";
        List<Order> data = orderMapper.selectPageByOvertime(offset, pageSize, overtime2);
        Integer total = orderMapper.selectTotalByOvertime(overtime2);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    @PostMapping("/updateorder")//编辑订单信息
    public String save2(@RequestBody Order order){
        System.out.println(order.toString());
        if (order.getOid() == null) {
            return "失败：oid不能为空";
        }
        int i = orderMapper.editOrder(order);
        if (i > 0) {
            return "成功";
        } else {
            return "失败";
        }
    }

    @PostMapping("/overOrder")//修改订单状态为已完成
    public String save(@RequestBody Order order){
        if (order.getOid() == null) {
            return "失败：oid不能为空";
        }
        int i = orderMapper.overOrder(order);
        if (i > 0) {
            return "成功";
        } else {
            return "失败";
        }
    }

    @GetMapping("/deleteorderbyoid")//根据订单id，删除对应的订单信息
    public int delete(Order order){
        if(order.getOid() == null){
            return 2;
        }else {
            return orderMapper.deleteOrder(order);
        }
    }

    @PostMapping("/orderbatch")//批量处理订单
    public int overBatch(@RequestBody List<String> ids) {
        orderMapper.overSelectOrder(ids);
        return 1;
    }

    @PostMapping("/deleteorders")//批量删除顾客信息
    public int deleteBatch(@RequestBody List<String> ids) {
        orderMapper.deleteOrdersByOid(ids);
        return 1;
    }
}
