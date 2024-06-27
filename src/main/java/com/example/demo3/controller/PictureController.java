package com.example.demo3.controller;

import com.example.demo3.entity.Picture;
import com.example.demo3.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/pictures")
public class PictureController {

    @Autowired
    private PictureMapper pictureMapper;

    @Value("${picture.upload.dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public Map<String, String> uploadPicture(@RequestParam("file") MultipartFile file, @RequestParam("picname") String picname) {
        Map<String, String> result = new HashMap<>();
        if (file.isEmpty()) {
            result.put("message", "文件为空");
            return result;
        }

        // 使用前端传递的图片名称
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = picname + extension;

        // 文件保存路径
        File dest = new File(uploadDir, filename);
        try {
            file.transferTo(dest);
            result.put("message", "成功");
            result.put("url", "/assets/" + filename); // 返回相对路径
        } catch (IOException e) {
            e.printStackTrace();
            result.put("message", "失败");
        }
        return result;
    }

    @GetMapping("/all")
    public List<Picture> getAllPictures() {
        return pictureMapper.findAll();
    }

    @PostMapping("/delete")
    public String deletePicture(@RequestBody int picid) {
        try {
            // 根据 picid 查询图片信息，获取图片名称 picname
            Picture picture = pictureMapper.findPicnameById(picid);
            if (picture == null) {
                return "失败";
            }

            // 删除数据库中的图片记录
            int result = pictureMapper.deleteById(picid);
            if (result <= 0) {
                return "失败";
            }

            // 删除 static/assets 目录下对应的图片文件
            System.out.println(picture.getPicname());
            String filename = picture.getPicname() + "11.jpg"; // 假设图片后缀为 jpg
            Path filePath = Paths.get("D:/IdeaCode/CurriculumDesign2/demo3/src/main/resources/static/assets", filename);
            Files.delete(filePath);

            return "成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "失败";
        }
    }

    @PostMapping("/deleteSelected")
    public String deleteSelectedPictures(@RequestBody List<Integer> ids) {
        int result = pictureMapper.deleteSelected(ids);
        return result > 0 ? "成功" : "失败";
    }

    @PostMapping("/add")
    public String addPicture(@RequestBody Picture picture) {
        int result = pictureMapper.insert(picture);
        return result > 0 ? "成功" : "失败";
    }

    @PostMapping("/edit")
    public String editPicture(@RequestBody Picture picture) {
        int result = pictureMapper.edit(picture);
        return result > 0 ? "成功" : "失败";
    }
}