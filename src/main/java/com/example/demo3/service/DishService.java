package com.example.demo3.service;

import com.example.demo3.entity.Dish;
import com.example.demo3.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DishService {
    @Autowired
    private DishMapper dishMapper;

    public int save(Dish dish) {
        if (dish.getDishId() > 0) {
            // 通过 dishId 查找菜品，确保存在
            Dish existingDish = dishMapper.findById(dish.getDishId());
            if (existingDish != null) {
                // 更新已有菜品
                return dishMapper.updateDish(dish);
            } else {
                // 如果不存在该 dishId 的菜品，可以选择抛出异常或插入新的菜品
                throw new IllegalArgumentException("菜品不存在，无法更新");
            }
        } else {
            // 插入新菜品
            return dishMapper.insertDish(dish);
        }
    }

    public int deleteDishByNames(List<String> dishNames) {
        int count = 0;
        for (String dishName : dishNames) {
            Dish dish = new Dish();
            dish.setDishName(dishName);
            count += dishMapper.deleteDish(dish);
        }
        return count;
    }

    public List<Dish> findAll() {
        return dishMapper.findAllDish();
    }

    public Map<String, Object> findAllType() {
        List<String> types = dishMapper.findAllType();
        Map<String, Object> result= new HashMap<>();
        result.put("categories", types);
        return result;
    }

    public List<Dish> findByDishName(String dishName) {
        return dishMapper.findByDishName(dishName);
    }

    public List<Dish> findByDishType(String type) {
        return dishMapper.findByDishType(type);
    }
    public List<Dish> findPage(int offset, int pageSize) {
        return dishMapper.selectPage(offset, pageSize);
    }


    public int getTotal() {
        return dishMapper.selectTotal();
    }

    public List<Dish> findPageByCriteria(int offset, int pageSize, String dishName, String type) {
        return dishMapper.selectPageByCriteria(offset, pageSize, dishName, type);
    }

    public int getTotalByCriteria(String dishName, String type) {
        return dishMapper.selectTotalByCriteria(dishName, type);
    }
}
