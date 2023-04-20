package com.lsd.myoauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * desc:授权服务器配置类
 * <p>
 * points:
 * 在认证服务器的配置中，通常需要实现以下内容：
 * <p>
 * 1. 客户端信息配置：配置客户端ID、客户端密钥等信息。
 * <p>
 * 2. 用户认证：定义用户认证方式，并配置用户认证信息。
 * <p>
 * 3. 授权类型：定义授权类型，比如授权码模式、密码模式等。
 * <p>
 * 4. 令牌存储：定义令牌存储方式，比如使用JWT。
 * <p>
 * 5. 用户批准：定义用户批准方式。
 * <p>
 * 6. 授权服务器端点：定义授权服务器端点，包括授权端点、令牌端点、用户信息端点等。
 * <p>
 * 7. 其他配置：比如跨域访问配置等。
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    // 令牌存储服务
    @Resource
    private TokenStore tokenStore;

    @Resource(name = "jwtAccessTokenConverter")
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource
    private JwtTokenEnhancer jwtTokenEnhancer;

//    @Resource
//    private CustomClientDetailsServiceImpl customClientDetailsService;


    // OAuth2认证授权异常处理入口点，它负责处理在OAuth2认证过程中的异常，例如用户认证失败、无法授权等
    // 该异常处理入口点会将异常信息转换为OAuth2标准的JSON格式，然后返回给客户端
    // 注入到认证服务器中是为了确保在发生认证异常时，能够正确地处理异常并提供适当的响应。


    // 配置授权服务器端点，如令牌存储，令牌自定义，用户批准和授权类型、但不包含端点安全配置

    /**
     * 密码授权模式的配置
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //TokenEnhancerChain是TokenEnhance的一个实现类
        TokenEnhancerChain chain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter);//还要把转换器放进去用来实现jwtTokenEnhancer的互相转换
        chain.setTokenEnhancers(delegates);
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                //可以看到主要是增加了 JwtAccessTokenConverter JWT访问令牌转换器和JwtTokenStore JWT令牌存储组件，
                //通过AuthorizationServerEndpointsConfigurer 授权服务器端点配置加入两个实例
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(chain); //设置JWT增强内容


    }

    // 该方法用于配置客户端详情服务,单租户从数据库表中读取客户端信息，多租户从redis中读取客户端信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//         clients.withClientDetails(customClientDetailsService);

        clients.inMemory() //.inMemory()放入内存。我们为了方便，直接放在内存中生成client，正常情况下是我们主动找授权服务器注册的时候才会有处理。
                .withClient("client") //指定client。参数为唯一client的id
                .secret(passwordEncoder.encode("112233")) //指定密钥
                .redirectUris("http://www.baidu.com") //指定重定向的地址,通过重定向地址拿到授权码。
                //.redirectUris("http://localhost:8081/login") //单点登录到另一服务器
                .accessTokenValiditySeconds(60 * 10) //设置Access Token失效时间
                .refreshTokenValiditySeconds(60 * 60 * 24) //设置refresh token失效时间
                .scopes("all") //指定授权范围
                .autoApprove(true) //自动授权，不需要手动允许了
                /**
                 * 授权类型：
                 * "authorization_code" 授权码模式
                 * "password"密码模式
                 * "refresh_token" 刷新令牌
                 */
                .authorizedGrantTypes("authorization_code", "password", "refresh_token"); //指定授权类型 可以多种授权类型并存。

    }

    // 配置授权服务器端点的安全,也可以用来实现单点登录
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()")
//                .allowFormAuthenticationForClients();
////                .authenticationEntryPoint(authExceptionEntryPoint);
//    }


    // 配置token管理服务
//    @Bean
//    @Primary
//    public AuthorizationServerTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore);
//        defaultTokenServices.setSupportRefreshToken(true);
//        return defaultTokenServices;
//    }

}

