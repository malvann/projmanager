package com.my.projmanager.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppBean {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static SimpleDateFormat dateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean
    public CacheManager cacheManager(){
        CaffeineCacheManager userAdmins = new CaffeineCacheManager("positions", "employees", "tasks");
        userAdmins.setCaffeine(cacheProperties());
        return userAdmins;
    }

    public Caffeine<Object, Object> cacheProperties(){
        return Caffeine.newBuilder()
                .initialCapacity(10)
                .maximumSize(50)
                .expireAfterAccess(10, TimeUnit.HOURS)
                .weakKeys()
                .recordStats();
    }
}
