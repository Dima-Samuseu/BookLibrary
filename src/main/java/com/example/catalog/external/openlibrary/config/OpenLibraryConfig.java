package com.example.catalog.external.openlibrary.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "open-library.settings")

public class OpenLibraryConfig {

    private String baseUrl;

}
