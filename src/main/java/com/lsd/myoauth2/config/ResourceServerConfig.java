package com.lsd.myoauth2.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lsd.myoauth2.service.Impl.CustomClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * 资源服务器配置类
 *
 * 1. 配置安全策略，比如授权访问、跨域请求等。
 *
 * 2. 配置资源服务器，指定资源服务器ID、配置资源服务器策略等。
 *
 * 3. 配置HTTP访问控制，包括哪些资源需要保护、哪些需要公开访问等。
 *
 * 4. 配置资源服务器过滤器，拦截请求并验证请求者的身份。
 *
 * 5. 配置基于令牌的访问控制，确保只有持有有效令牌的请求者才能访问受保护的资源。
 *
 * 6. 实现资源服务器业务逻辑，根据实际需求，实现处理请求的业务逻辑，比如数据查询、数据处理等。
 *
 * 7. 配置Session管理，包括session的创建策略，常用的策略有STATELESS、IF_REQUIRED、NEVER、STATEFUL等。
 */

@Configuration
@EnableResourceServer //开启资源服务器
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private TokenStore tokenStore;

//    @Resource
//    private RemoteTokenServices remoteTokenServices;

//    @Resource
//    private OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler;

//    // 需要在配置文件中配置，包括clientId、clientSecret、checkTokenEndpointUrl等
//    @Resource
//    private OAuth2ClientProperties oAuth2ClientProperties;

//    @Resource
//    private CustomClientDetailsServiceImpl customClientDetailsService;



//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources){
//        resources.tokenStore(tokenStore);
//        String clientId = customClientDetailsService.loadClientByClientId("client").toString();
//        if (StringUtils.isNotBlank(clientId)) {
//            resources.resourceId(clientId);
//        }
//    }

    // 配置资源服务器的安全策略
    //  hasRole这里使用了在OAuth2WebSecurityExpressionHandler中注入的SecurityExpressionRoot对象
    //  而SecurityExpressionRoot是注入到容器中的，所以可以直接使用，其实可以看出这里其实是SercuityConfig的配置
    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/api/**","/user/**").permitAll()
//                .antMatchers("/api/user/save").hasAuthority("admin")
//                .anyRequest().authenticated();
        http.requestMatchers().antMatchers("/api/**","/user/**").and()
                .authorizeRequests()//授权的请求
                //进行接口的鉴权处理
                .antMatchers("/api/user/save").hasAuthority("admin")
                //其余接口不做鉴权，只需要认证即可
                .anyRequest()
                .authenticated();
    }

    // 把applicationContext注入到OAuth2WebSecurityExpressionHandler中
//    @Bean
//    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
//        OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler = new OAuth2WebSecurityExpressionHandler();
//        oAuth2WebSecurityExpressionHandler.setApplicationContext(applicationContext);
//        return oAuth2WebSecurityExpressionHandler;
//    }

}
