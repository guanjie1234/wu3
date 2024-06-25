package com.example.javaweb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class SpringSessionConfig {
    // 最新的chrome，设置null会默认成lax 但是如果设置samesite为NONE,又需要设置secure。https支持secure，http不行
    @Bean
    public CookieSerializer httpSessionIdResolver() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setUseHttpOnlyCookie(false);
        cookieSerializer.setSameSite("None");
        cookieSerializer.setCookiePath("/");
        cookieSerializer.setUseSecureCookie(true);
        return cookieSerializer;
    }

}
