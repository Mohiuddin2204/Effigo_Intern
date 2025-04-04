package com.template.quesans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class ProfileConfig {

    private static final Logger logger = LoggerFactory.getLogger(ProfileConfig.class);

    @Bean
    @Profile("dev")
    public CommandLineRunner devRunner() {
        return args -> {
            logger.info("Application is running in DEVELOPMENT mode");
            logger.debug("Debug logging is enabled for development");
        };
    }

    @Bean
    @Profile("test")
    public CommandLineRunner testRunner() {
        return args -> {
            logger.info("Application is running in TEST mode");
            logger.debug("Using in-memory database for testing");
        };
    }

    @Bean
    @Profile("prod")
    public CommandLineRunner prodRunner() {
        return args -> {
            logger.info("Application is running in PRODUCTION mode");
            logger.info("Debug logging is disabled for production");
        };
    }
}