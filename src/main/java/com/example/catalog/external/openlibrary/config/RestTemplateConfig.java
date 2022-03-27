package com.example.catalog.external.openlibrary.config;

import com.example.catalog.config.interceptor.CustomClientHttpRequestInterceptor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.annotation.Resource;

/**
 * The type Rest template config.
 */
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties
@Getter
@Setter
public class RestTemplateConfig {

    @Value("${open-library.base-uri}")
    private String url;
    /**
     * RestTemplate with default URL "https://openlibrary.org"
     *
     * @param builder the builder
     * @return the rest template
     */
    @Bean("openLibrary")
    @Resource
    public RestTemplate restTemplateAlfaBank(RestTemplateBuilder builder) {
        return builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url))
                .additionalInterceptors(new CustomClientHttpRequestInterceptor())
                .build();
    }
}
