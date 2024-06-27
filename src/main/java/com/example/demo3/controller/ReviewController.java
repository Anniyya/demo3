package com.example.demo3.controller;

import com.example.demo3.entity.Order;
import com.example.demo3.entity.Review;
import com.example.demo3.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ReviewController {
    @Autowired
    //自动注入实例化对象，springboot的功能
    private ReviewMapper reviewMapper;

    @GetMapping("/selectAllReview")//查询所有留言信息
    public List query(){
        List<Review> list = reviewMapper.selectAllReview();
        return list;//自动转换为Jason格式传给前端
    }

    @GetMapping("/selectAllReviewByPage")//分页查询所有留言信息，用于管理平台分页展示
    public Map<String, Object> findPage(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("rcontent") String rcontent) {
        int offset = (pageNum - 1) * pageSize;
        String rcontent2 = "%" + rcontent + "%";
        List<Review> data = reviewMapper.selectAllReviewByPage(offset, pageSize, rcontent2);
        Integer total = reviewMapper.selectTotalByRcontent(rcontent2);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    @PostMapping("/insertReview")//发布新留言
    public String save(@RequestBody Review review){
        int i = reviewMapper.insertReview(review);
        return "成功";
    }
}
