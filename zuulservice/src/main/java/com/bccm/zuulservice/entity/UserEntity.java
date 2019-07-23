package com.bccm.zuulservice.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserEntity implements UserDetails {

    private String username;

    private String password;


    private List<String> roles;

    public UserEntity(String username, String password,List<String> roles){
        this.username=username;
        this.password=password;
        this.roles=roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        if(!roles.isEmpty()){
            for(String role:roles) {
                GrantedAuthority au = new SimpleGrantedAuthority(role);
                list.add(au);
            }
        }
        return list;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {//用户账号是否不过期，false则验证不通过
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//用户账号是否不锁定，false则验证不通过
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//用户密码是否不过期，false则验证不通过
        return true;
    }

    @Override
    public boolean isEnabled() {//用户是否启用，false则验证不通过
        return true;
    }
}
