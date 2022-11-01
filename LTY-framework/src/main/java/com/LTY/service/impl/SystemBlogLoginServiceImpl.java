package com.LTY.service.impl;

import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.LoginUser;
import com.LTY.domin.entity.User;
import com.LTY.service.LoginService;
import com.LTY.utils.JwtUtil;
import com.LTY.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 刘泰源
 * @version 1.8
 */
@Service
public class SystemBlogLoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //判断认证是否通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("账号或密码错误");
        }
        //获取用户ID,生成Token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //生成JWT存到Redis中
        redisCache.setCacheObject("login:"+userId, loginUser);
        Map<String,String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseResult.okResult(map);
    }
}
