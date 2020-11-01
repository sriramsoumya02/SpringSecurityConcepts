package com.soumya.springsecurity.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;

@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    private String secreatKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfter;

    public JwtConfig() {
    }

    public String getSecreatKey() {
        return secreatKey;
    }

    public void setSecreatKey(String secreatKey) {
        this.secreatKey = secreatKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationAfter() {
        return tokenExpirationAfter;
    }

    public void setTokenExpirationAfter(Integer tokenExpirationAfter) {
        this.tokenExpirationAfter = tokenExpirationAfter;
    }

    public String getAuthoraizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
