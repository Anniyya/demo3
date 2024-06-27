package com.example.demo3.mapper;

import com.example.demo3.entity.Picture;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PictureMapper {
    @Select("SELECT * FROM picture")
    List<Picture> findAll();

    @Delete("DELETE FROM picture WHERE picid = #{picid}")
    int deleteById(int picid);

    @Delete("<script>" +
            "DELETE FROM picture WHERE picid IN " +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    int deleteSelected(@Param("list") List<Integer> ids);

    @Insert("INSERT INTO picture(picname, url, number) VALUES(#{picname}, #{url}, #{number})")
    @Options(useGeneratedKeys = true, keyProperty = "picid")
    int insert(Picture picture);

    @Update("update picture set picname = #{picname}, number = #{number} where picid = #{picid}")
    int edit(Picture picture);

    @Select("SELECT picname FROM picture WHERE picid = #{picid}")
    Picture findPicnameById(int picid);
}
