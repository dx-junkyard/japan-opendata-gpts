package com.dxjunkyard.opendata.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OpenDataPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenDataPlatformApplication.class, args);
    }

}
