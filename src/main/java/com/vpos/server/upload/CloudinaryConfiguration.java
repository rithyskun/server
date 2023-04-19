package com.vpos.server.upload;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rithy SKUN
 * @created 19/04/2023 - 9:41 AM
 * @project server
 **/

@Configuration
public class CloudinaryConfiguration {

    @Value("${application.cloudinary.cloud_name}")
    private String cloud_name;
    @Value("${application.cloudinary.api_key}")
    private String api_key;
    @Value("${application.cloudinary.api_secret}")
    private String api_secret;

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = null;
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloud_name);
        config.put("api_key", api_key);
        config.put("api_secret", api_secret);
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}
