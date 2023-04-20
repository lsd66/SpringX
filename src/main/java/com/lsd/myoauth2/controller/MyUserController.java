package com.lsd.myoauth2.controller;

import com.lsd.myoauth2.model.entity.MyUser;
import com.lsd.myoauth2.model.req.UserReq;
import com.lsd.myoauth2.service.Impl.MyUserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MyUserController {

    @Resource
    private MyUserServiceImpl myUserService;

    /**
     * 得到所有用户列表，所有权限可以访问
     *
     * @return
     */
    @PostMapping("/users")
    public Object getAllUsers() {
        return myUserService.list();
    }

    /**
     * 增加用户，只有权限为admin的用户才可以访问
     *
     * @param userReq
     */
    @PostMapping("/save")
    public Object save(@RequestBody UserReq userReq) {
        MyUser myUser = new MyUser();
        myUser.setName(userReq.getName());
        myUser.setPassword(userReq.getPassword());
        myUser.setAccount(userReq.getAccount());
        myUser.setDescription(userReq.getDescription());
        return myUserService.save(myUser);
    }
}
