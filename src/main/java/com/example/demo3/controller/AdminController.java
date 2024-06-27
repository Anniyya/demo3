package com.example.demo3.controller;

import com.example.demo3.entity.Admin;
import com.example.demo3.mapper.AdminMapper;
import com.example.demo3.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;

    @PostMapping("/insertadmin")
    public String save(@RequestBody Admin admin){
        if (admin.getAid() == 0) {
            return "失败：aid不能为空";
        }
        int i = adminService.save(admin);
        if (i > 0) {
            return "成功";
        } else {
            return "失败";
        }
    }

    @GetMapping("/findadmin")
    public List<Admin> query(){
        return adminService.findAll();
    }


    @GetMapping("/findadminbyaidpwd")
    public Map<String, Object> queryBy(int aid,String apwd){
        Admin admin = adminService.findAdminByAid(aid,apwd);
        Map<String, Object> res = new HashMap<>();

        if(admin == null){
            res.put("admin",admin);
            res.put("msg","查询失败");
        }else{
            res.put("admin",admin);
            res.put("msg","查询成功");
            res.put("token","登录成功");
        }
        return res;
    }

    @DeleteMapping ("/deleteadmin")
    public int delete(Admin admin){
        if(admin.getAid() == 0){
            return 2;
        }else {
            return adminMapper.deleteAdmin(admin);
        }
    }

    @DeleteMapping("/deletebatch")
    public int deleteBatch(@RequestBody List<String> ids) {
        return adminService.deleteAdminById(ids);
    }



    @GetMapping("/page")
    // @RequestParam接受?pageNum=1&pageSize=10
    public Map<String, Object> findPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        List<Admin> data = adminService.findPage(offset, pageSize);
        int total = adminService.getTotal();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    @GetMapping("/pageSelectbyname")//通过姓名模糊分页查询
    public Map<String, Object> findPage(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("aname") String aname) {
        int offset = (pageNum - 1) * pageSize;
        String aname2 = "%" + aname + "%";
        List<Admin> data = adminService.findPageByName(offset, pageSize, aname2);
        int total = adminService.getTotalByName(aname2);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}
