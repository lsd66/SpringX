//package com.lsd.myoauth2.service.Impl;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * desc:远程token服务
// * 可以向授权服务器发送请求，以验证传递的访问令牌是否有效。
// */
//@Service
//public class RemoteTokenService {
//
//    @Resource
//    private AccessTokenConverter accessTokenConverter;
//
//    @Bean
//    public ResourceServerTokenServices resourceServerTokenServices() {
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        // 通过访问远程授权服务器 check_token 端点验证令牌
//        remoteTokenServices.setCheckTokenEndpointUrl("http://auth-server-url/oauth/check_token");
//        // 客户端id
//        remoteTokenServices.setClientId("client_id");
//        // 客户端密钥
//        remoteTokenServices.setClientSecret("client_secret");
//        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
//        return remoteTokenServices;
//    }
//
//    @Bean
//    public AccessTokenConverter accessTokenConverter() {
//        return new DefaultAccessTokenConverter();
//    }
//
//}
