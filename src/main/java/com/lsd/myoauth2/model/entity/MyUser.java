package com.lsd.myoauth2.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 官方提供了一个UserDetails接口，我们可以实现这个接口，
 * 然后重写里面的方法，来实现自己的用户认证逻辑
 */

@Data
@TableName("user")
public class MyUser  implements UserDetails {

    @TableId(value = "id")
    private Integer id;

    @TableField(value = "account")
    private String account;

    @TableField(value = "description")
    private String description;

    @TableField(value = "password")
    private String password;

    @TableField (value = "name")
    private String name;

    @TableField(exist = false)
    private List<Role> roles;

    @TableField(exist = false)
    private List<GrantedAuthority> authorities;

    public MyUser() {
    }

    // 单独定义一个构造方法
    public MyUser(String name, String password, List<GrantedAuthority> authorities) {
        this.name = name;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
