package com.Gogedit.configuration.properties;

import lombok.Getter;
import org.springframework.web.cors.CorsConfiguration;

@Getter
public class CorsProperties {

    private final CorsConfiguration cors = new CorsConfiguration();

    public CorsConfiguration getCors() {
        return cors;
    }
}
