package com.example.demo3.mapper;


import com.example.demo3.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface AdminMapper {
    @Select("SELECT * FROM admin")//查询所有
    List<Admin> findAllAdmin();

    @Select("SELECT COUNT(*) FROM admin")//查询所有获得数量
    int getTotle();

    @Select("SELECT * FROM admin WHERE aid = #{aid} AND apwd = #{apwd}")//查询是否有登录用户
    Admin getAdminByAid(@Param("aid") Integer aid,@Param("apwd") String apwd);
    @Insert("INSERT INTO admin(aid,apwd,aname,aphone,aaddress) VALUES (#{aid},#{apwd},#{aname},#{aphone},#{aaddress})")
    int insertAdmin(Admin admin);

    @Update("UPDATE admin SET apwd=#{apwd}, aname=#{aname}, aphone=#{aphone}, aaddress=#{aaddress} WHERE aid=#{aid}")
    int updateAdmin(Admin admin);

    @Delete("DELETE FROM admin WHERE aid=#{aid}")
    int deleteAdmin(Admin admin);

    @Select("SELECT * FROM admin LIMIT #{offset},#{pageSize}")
    List<Admin> selectPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM admin")
    int selectTotal();

    @Select("SELECT * FROM admin WHERE aname LIKE CONCAT('%', #{aname}, '%') LIMIT #{offset}, #{pageSize}")
    List<Admin> selectPageByName(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize,@Param("aname") String aname);

    @Select("SELECT COUNT(*) FROM admin WHERE aname LIKE #{aname}")
    int selectTotalByName(String aname);
}
