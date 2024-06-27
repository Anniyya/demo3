package com.example.demo3.mapper;

import com.example.demo3.entity.Order;
import com.example.demo3.entity.Review;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReviewMapper {

    @Select("SELECT * FROM review ORDER BY rtime DESC")
    List<Review> selectAllReview();

    @Insert("insert into review (rscore, rcontent, rtime, cid) values (#{rscore}, #{rcontent}, #{rtime}, #{cid})")
    int insertReview(Review review);


    @Select("SELECT * FROM review WHERE rcontent LIKE CONCAT('%', #{rcontent}, '%') LIMIT #{offset}, #{pageSize}")
    List<Review> selectAllReviewByPage(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("rcontent") String rcontent);

    @Select("SELECT count(*) FROM review WHERE rcontent LIKE CONCAT('%', #{rcontent}, '%')")
    Integer selectTotalByRcontent(@Param("rcontent") String rcontent);
}
