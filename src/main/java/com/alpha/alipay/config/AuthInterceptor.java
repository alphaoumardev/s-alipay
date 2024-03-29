package com.alpha.alipay.config;

import cn.hutool.core.util.StrUtil;
import com.alpha.alipay.exception.CustomException;
import com.alpha.alipay.mappers.UserMapper;
import com.alpha.alipay.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@AllArgsConstructor
public class AuthInterceptor implements HandlerInterceptor
{

    @Autowired
    private UserMapper userMapper;

    public AuthInterceptor() {}

    @Override
    public boolean preHandle(HttpServletRequest request,  HttpServletResponse response,  Object handler) {

        String token = request.getHeader("token");
        if (StrUtil.isBlank(token))
        {
            throw new CustomException("401", "未获取到token, 请重新登录");
        }
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        User user = userMapper.selectById(userId);
        if (user == null)
        {
            throw new CustomException("401", "token不合法");
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();

        try
        {
            jwtVerifier.verify(token);
        }
        catch (Exception e)
        {
            throw new CustomException("401", "token不合法");
        }
        return true;
    }
}
