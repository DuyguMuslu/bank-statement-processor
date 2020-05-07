package com.abc.io.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.abc.io.ingest")
public class WebTestConfig extends WebMvcConfigurationSupport {
    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }
}