package com.bccm.zuulservice.provider;

import com.bccm.zuulservice.entity.UserEntity;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //登录用户名&密码
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        //获取用户信息
        UserEntity user = new UserEntity(username,username,new ArrayList<String>());
        //校验
//        if(!password.equals(user.getPassword()))
//            throw new BadCredentialsException("Wrong password.");
       // System.out.println(username);
        if(user == null)
            throw new BadCredentialsException("Username not found.");
        //--将登录用户信息存入redis
        //redisTemplate.opsForValue().set(username,user);
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
