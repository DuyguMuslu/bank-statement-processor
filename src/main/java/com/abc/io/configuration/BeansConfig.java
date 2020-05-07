package com.abc.io.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */

@Configuration
public class BeansConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
