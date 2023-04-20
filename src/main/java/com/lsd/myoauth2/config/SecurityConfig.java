package com.lsd.myoauth2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AuthenticationConfiguration authenticationConfiguration;


    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedPage("/unauth.html");

        http.authorizeRequests()
                //放行授权服务器的几个端点请求、登录请求、登出请求。
                .antMatchers("/oauth/**", "/login/**", "/logout/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                //.and() 就相当于回到 http再继续配置
                .and()
                //放行所有的表单请求
                .formLogin()
                .permitAll()
                .and()
                //关闭csrf
                .csrf().disable();
    }

    /**
     * 密码授权模式用到的AuthenticationManager类
     *
     * @return
     * @throws Exception
     */
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
