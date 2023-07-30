package com.Gogedit.configuration;

import com.Gogedit.configuration.properties.CorsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Configuration
//@RequiredArgsConstructor
//@Log4j2
public class CorsConfiguration {

//    private final CorsProperties corsProperties;

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        org.springframework.web.cors.CorsConfiguration config = corsProperties.getCors();
//        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
//            log.debug("Registering CORS filter");
//            source.registerCorsConfiguration("/**", config);
//        }
//        return new CorsFilter(source);
//    }
}
