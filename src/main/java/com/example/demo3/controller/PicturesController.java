package com.example.demo3.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping
@CrossOrigin
public class PicturesController {

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public Response uploadPicture(@RequestParam("file") MultipartFile file, @RequestParam("position") int position) {
        String fileName = "image" + (position + 1) + ".jpg";
        File dest = new File(uploadPath, fileName);

        try {
            FileUtils.writeByteArrayToFile(dest, file.getBytes());
            return new Response(true, "图片上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(false, "图片上传失败");
        }
    }

    static class Response {
        private boolean success;
        private String message;

        public Response(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

