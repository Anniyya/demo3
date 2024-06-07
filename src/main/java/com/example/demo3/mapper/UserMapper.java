package com.example.demo3.mapper;

import com.example.demo3.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from admins")
    public List<User> find();

    @Insert("insert into admins values (#{mmid},#{mpwd},#{mname})")
    public int insert(User user);
}
