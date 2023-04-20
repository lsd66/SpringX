package com.lsd.myoauth2.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsd.myoauth2.model.entity.MyClientDetails;
import com.lsd.myoauth2.model.mapper.MyClientDetailMapper;
import com.lsd.myoauth2.service.CustomClientDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CustomClientDetailsServiceImpl extends ServiceImpl<MyClientDetailMapper,MyClientDetails>  implements CustomClientDetailsService,ClientDetailsService {

    @Resource
    private MyClientDetailMapper myClientDetailMapper;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        MyClientDetails myClientDetails = myClientDetailMapper.selectById(s);
        if (myClientDetails == null) {
            throw new NoSuchClientException("No client with requested id: " + s);
        }
        // 因为继承了BaseClientDetails，所以这里可以直接返回
        return myClientDetails;
    }
}
