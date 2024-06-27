package com.example.demo3.service;

import com.example.demo3.entity.Admin;
import com.example.demo3.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public int save(Admin admin) {
        if (admin.getAid() == 0) {
            return adminMapper.insertAdmin(admin);
        } else {
            return adminMapper.updateAdmin(admin);
        }
    }

    public int deleteAdminById(List<String> ids) {
        int count = 0;
        for (String id : ids) {
            Admin admin = new Admin();
            admin.setAid(Integer.parseInt(id));
            count += adminMapper.deleteAdmin(admin);
        }
        return count;
    }

    public List<Admin> findAll() {
        return adminMapper.findAllAdmin();
    }
    public Admin findAdminByAid(int aid,String apwd) {
        return adminMapper.getAdminByAid(aid,apwd);
    }
    public List<Admin> findPage(int offset, int pageSize) {
        return adminMapper.selectPage(offset, pageSize);
    }

    public int getTotal() {
        return adminMapper.selectTotal();
    }

    public List<Admin> findPageByName(int offset, int pageSize, String name) {
        return adminMapper.selectPageByName(offset, pageSize, name);
    }

    public int getTotalByName(String name) {
        return adminMapper.selectTotalByName(name);
    }
}
