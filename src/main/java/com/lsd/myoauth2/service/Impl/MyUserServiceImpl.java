package com.lsd.myoauth2.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsd.myoauth2.model.entity.MyUser;
import com.lsd.myoauth2.model.mapper.MyUserMapper;
import com.lsd.myoauth2.service.MyUserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class MyUserServiceImpl  extends ServiceImpl<MyUserMapper,MyUser> implements  MyUserService, UserDetailsService {

    @Resource
    private MyUserMapper myUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //  调用自定义的构造函数创建一个MyUser对象返回
        LambdaQueryWrapper<MyUser> wrapper = new LambdaQueryWrapper();
        String name = myUserMapper.selectOne(wrapper.eq(MyUser::getName, s)).getName();
        if (name == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        String password = myUserMapper.selectOne(wrapper.eq(MyUser::getName, s)).getPassword();
        String authority = myUserMapper.selectOne(wrapper.eq(MyUser::getName, s)).getAccount();
        return new MyUser(name, password,AuthorityUtils.commaSeparatedStringToAuthorityList(authority));
    }

}
