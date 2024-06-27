package com.example.demo3.controller;


import com.example.demo3.entity.Reply;
import com.example.demo3.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ReplyController {
    @Autowired
    //自动注入实例化对象，springboot的功能
    private ReplyMapper replyMapper;

    @GetMapping("/selectAllReplyByRid")//查询对应留言的所有回复
    public List query(@RequestParam("rid")Integer rid){
        List<Reply> list = replyMapper.selectAllReplyByRid(rid);
        return list;//自动转换为Jason格式传给前端
    }

    @PostMapping("/insertReply")
    public int save(@RequestBody Reply newReply){
        return replyMapper.insertReply(newReply);
    }
}
