package com.my.projmanager.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("jwtconfig")
public class JwtTokenConfig {
    private String secret;
    private Long expiration;
}
