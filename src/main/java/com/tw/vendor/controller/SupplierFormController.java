package com.tw.vendor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.vendor.model.Vendor;
import com.tw.vendor.service.VendorService;

@CrossOrigin(origins = "*")
@RestController  // 標示該類別與前端交互，自動轉Json
@RequestMapping("/vendor")
public class SupplierFormController {
    
    @Autowired  // 注入studentRepository實例 (dao)
    private VendorService vendorService;

    @GetMapping("/supplier")  // 對應前端get,  傳資料給前端
    public List<Vendor> getveVendors() {
        return vendorService.findAll();
    }
    
    @PostMapping("/supplieradd")
    public String processStudent(@RequestBody Vendor vendor){
        vendorService.save(vendor);
        return "成功拿到資料";
    }
    
}
