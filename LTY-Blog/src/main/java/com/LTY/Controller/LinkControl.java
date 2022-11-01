package com.LTY.Controller;

import com.LTY.annotation.SystemLog;
import com.LTY.domin.ResponseResult;
import com.LTY.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘泰源
 * @version 1.8
 */
@RestController
@RequestMapping("/link")
public class LinkControl {
    @Autowired
    private LinkService linkService;
    @SystemLog(BusinessName = "获取所有的友链")
    @RequestMapping("/getAllLink")
    public ResponseResult getAllLink(){
         return linkService.getAllLink();
    }
}
