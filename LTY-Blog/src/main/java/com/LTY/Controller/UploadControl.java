package com.LTY.Controller;

import com.LTY.annotation.SystemLog;
import com.LTY.domin.ResponseResult;
import com.LTY.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘泰源
 * @version 1.8
 */
@RestController
public class UploadControl {
    @Autowired
    private UploadService uploadService;
    @SystemLog(BusinessName = "上传照片")
    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile img){
       return uploadService.upload(img);
    }
}
