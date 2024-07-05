package com.example.demo3.mapper;

import com.example.demo3.entity.Dish;
import com.example.demo3.entity.Picture;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {
    @Select("SELECT * FROM dish")//查询所有
    List<Dish> findAllDish();

    @Select("SELECT * FROM dish WHERE dishId = #{dishId}")//通过菜品id查询
    Dish findById(int dishId);
    @Select("SELECT * FROM dish WHERE dishName = #{dishName}")//通过菜品查询
    List<Dish> findByDishName(String dishName);

    @Select("SELECT * FROM dish WHERE type = #{type}")//查询某个类别的菜品
    List<Dish> findByDishType(String type);
    @Insert("INSERT INTO dish(type,price,specification,dishName,picture,sale) VALUES (#{type},#{price},#{specification},#{dishName},#{picture},0)")
    @Options(useGeneratedKeys = true, keyProperty = "dishId")
    int insertDish(Dish dish);

    @Update("UPDATE dish SET type=#{type},price=#{price},specification=#{specification},picture=#{picture} ,dishName=#{dishName} WHERE dishId = #{dishId}")
    int updateDish(Dish dish);

    @Delete("DELETE FROM dish WHERE dishName=#{dishName}")
    int deleteDish(Dish dish);

    @Select("SELECT DISTINCT type FROM dish")
    List<String>findAllType();

    @Select("SELECT * FROM dish LIMIT #{offset},#{pageSize}")
    List<Dish> selectPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM dish")
    int selectTotal();


    @Select("SELECT * FROM dish WHERE dishName LIKE #{dishName} AND type LIKE #{type} LIMIT #{offset}, #{pageSize}")
    List<Dish> selectPageByCriteria(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize,
                                    @Param("dishName") String dishName, @Param("type") String type);

    @Select("SELECT COUNT(*) FROM dish WHERE dishName LIKE #{dishName} AND type LIKE #{type}")
    int selectTotalByCriteria(@Param("dishName") String dishName, @Param("type") String type);

    @Select("SELECT dishName , sale \n" +
            "FROM dish\n" +
            "ORDER BY sale DESC\n" +
            "LIMIT 5;\n")
    List<Dish> findTopFiveBySale();

    @Select("SELECT * FROM dish WHERE dishName LIKE CONCAT('%', #{keyWord}, '%')")
    List<Dish> findByKeyWord(String keyWord);


}
