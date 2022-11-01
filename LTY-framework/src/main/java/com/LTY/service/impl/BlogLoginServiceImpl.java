package com.LTY.service.impl;

import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.LoginUser;
import com.LTY.domin.entity.User;
import com.LTY.domin.vo.BlogUserLoginVo;
import com.LTY.domin.vo.UserInfoVo;
import com.LTY.service.BlogLoginService;
import com.LTY.utils.BeanCopyUtils;
import com.LTY.utils.JwtUtil;
import com.LTY.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 刘泰源
 * @version 1.8
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {
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
        redisCache.setCacheObject("bloglogin"+userId, loginUser);
        //把User转换成UserInfo进行返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        //把jwt和UserInfo封装返回
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt,userInfoVo);
        return ResponseResult.okResult(blogUserLoginVo);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public ResponseResult logout() {
        //从Token中获取userId
        //获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();

        //删除Redis中的数据
        redisCache.deleteObject("bloglogin"+id);
        return ResponseResult.okResult();
    }
}
