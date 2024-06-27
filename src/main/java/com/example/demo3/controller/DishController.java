package com.example.demo3.controller;

import com.example.demo3.entity.Dish;
import com.example.demo3.mapper.DishMapper;
import com.example.demo3.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishService dishService;


    @PostMapping("/insertdish")
    public Map<String, Object> save(@RequestBody Dish dish){
        Map<String, Object> result = new HashMap<>();
        String test1 = dish.getDishName();
        if (test1 == null) {
            result.put("message", "失败：菜名不能为空");
            result.put("status", "fail");
            return result;
        }
        int i = dishService.save(dish);
        if (i > 0) {
            result.put("message", "成功");
            result.put("status", "success");
        } else {
            result.put("message", "失败");
            result.put("status", "fail");
        }
        return result;
    }

    @GetMapping("/test")
    public Map<String, Object> hello(){
        Map<String, Object> result = new HashMap<>();
        result.put("message", "成功访问测试界面");
        return result;
    }

    @GetMapping("/finddish")
    public List<Dish> query(){
        return dishService.findAll();
    }

    @GetMapping("/findAllType")
    public Map<String, Object> findAllType(){
        return dishService.findAllType();
    }


    @GetMapping("/findDishByDishName")
    public List<Dish> findDishByDishName(String dishName){
        return dishService.findByDishName(dishName);
    }

    @GetMapping("/findDishByType")
    public List<Dish> findDishByType(String type){
        return dishService.findByDishType(type);
    }


    @DeleteMapping ("/deletedish")
    public Map<String, Object> delete(Dish dish){
        Map<String, Object> result = new HashMap<>();
        if(dish.getDishName() == null){
            result.put("message", "成功");
            result.put("status", "success");
            return result;
        }else {
            int flag = dishMapper.deleteDish(dish);
            if (flag == 0){
                result.put("message", "失败");
                result.put("status", "fail");
            }else{
                result.put("message", "成功");
                result.put("status", "success");
            }
            return result;
        }
    }

    @DeleteMapping("/deletebatch")
    public int deleteBatch(@RequestBody List<String> dishNames) {
        return dishService.deleteDishByNames(dishNames);
    }



    @GetMapping("/page")
    // @RequestParam接受?pageNum=1&pageSize=10
    public Map<String, Object> findPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        List<Dish> data = dishService.findPage(offset, pageSize);
        int total = dishService.getTotal();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    @GetMapping("/select")//通过菜名模糊分页查询
    public Map<String, Object> findPage(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam(value = "dishName", required = false) String dishName,
                                        @RequestParam(value = "type", required = false) String type) {
        if (dishName == null) {
            dishName = "";
        }
        if (type == null) {
            type = "";
        }
        int offset = (pageNum - 1) * pageSize;
        String dishName2 = "%" + dishName + "%";
        String type2 = "%" + type + "%";
        List<Dish> data = dishService.findPageByCriteria(offset, pageSize, dishName2, type2);
        int total = dishService.getTotalByCriteria(dishName2, type2);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }


}
