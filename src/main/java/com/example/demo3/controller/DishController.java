package com.example.demo3.controller;

import com.example.demo3.entity.Dish;
import com.example.demo3.mapper.DishMapper;
import com.example.demo3.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        String dishName = dish.getDishName();
        if (dishName == null || dishName.isEmpty()) {
            result.put("message", "失败：菜名不能为空");
            result.put("status", "fail");
            return result;
        }

        int rowsAffected = dishService.save(dish);
        if (rowsAffected > 0) {
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
    public Map<String, Object> findDishByType(String type){
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
            // 删除 static/assets 目录下对应的图片文件
            String filename = dish.getDishName() + ".jpg"; // 假设图片后缀为 jpg
            Path filePath = Paths.get("D:/IdeaCode/CurriculumDesign2/demo3/src/main/resources/static/assets", filename);
            try {
                Files.delete(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

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

    @PostMapping("/deletebatch")
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

    @GetMapping("/topfive")
    public List<Dish> getTopFiveDishesBySale() {
        return dishMapper.findTopFiveBySale();
    }

    @Value("${picture.upload.dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public Map<String, String> uploadPicture(@RequestParam("file") MultipartFile file, @RequestParam("dishName") String dishName) {
        Map<String, String> result = new HashMap<>();
        if (file.isEmpty()) {
            result.put("message", "文件为空");
            return result;
        }

        // 使用前端传递的图片名称
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = dishName + extension;

        // 文件保存路径
        File dest = new File(uploadDir, filename);
        try {
            file.transferTo(dest);
            result.put("message", "成功");
            result.put("picture", "/assets/" + filename); // 返回相对路径
        } catch (IOException e) {
            e.printStackTrace();
            result.put("message", "失败");
        }
        return result;
    }

    @GetMapping("/findDishByKeyWord")
    public List<Dish> findDishByKeyWord(String keyWord){
        return dishService.findByKeyWord(keyWord);
    }

}
