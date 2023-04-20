package com.lsd.myoauth2.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:自定义token增强器
 * <p>
 * OAuth2AccessToken是OAuth2协议中的一个标准，它代表了一个访问令牌，它包含了访问令牌本身以及一些附加信息。
 */

public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> info = new HashMap();
        info.put("enhance", "增强的信息");
        //给的参数是oAuth2的AccessToken，实现类是DefaultOAuth2AccessToken，
        //里面有个setAdditionalInformation方法添加自定义信息（Map类型）
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }

}
