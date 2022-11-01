package com.LTY.service;

import com.LTY.domin.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘泰源
 * @version 1.8
 */
public interface UploadService {
    ResponseResult upload(MultipartFile img);
}
