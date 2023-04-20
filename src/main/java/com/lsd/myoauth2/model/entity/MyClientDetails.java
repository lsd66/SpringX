package com.lsd.myoauth2.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * desc: 用于映射客户端信息表的记录
 * 官方提供了一个默认的客户端表结构，但是不支持多租户，所以这里自己定义了一个
 */

@Data
@TableName("client_details")
public class MyClientDetails implements ClientDetails {
    @TableId(value = "client_id")
    private String clientId;

    @TableField(value = "client_secret")
    private String clientSecret;

    @TableField(value = "scope")
    private String scope;

    @TableField(value = "authorized_grant_types")
    private String authorizedGrantTypes;

    @TableField(value = "authorities")
    private String authorities;

    @TableField(value = "access_token_validity")
    private Integer accessTokenValiditySeconds;

    @TableField(value = "refresh_token_validity")
    private Integer refreshTokenValiditySeconds;

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(this.authorities);
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return StringUtils.commaDelimitedListToSet(this.authorizedGrantTypes);
    }

    @Override
    public Set<String> getScope() {
        return StringUtils.commaDelimitedListToSet(this.scope);
    }


}
