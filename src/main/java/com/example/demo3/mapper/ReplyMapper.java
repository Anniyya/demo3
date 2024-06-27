package com.example.demo3.mapper;

import com.example.demo3.entity.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReplyMapper {

    @Select("SELECT * FROM reply WHERE rid = #{rid} ORDER BY ptime DESC")
    List<Reply> selectAllReplyByRid(Integer rid);

    @Insert("insert into reply (pcontent,ptime,cid,aid,rid) values (#{pcontent},#{ptime},#{cid},#{aid},#{rid})")
    int insertReply(Reply reply);
}
