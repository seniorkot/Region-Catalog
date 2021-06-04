package com.seniorkot.regioncatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main application class.
 *
 * @author seniorkot
 */
@EnableCaching
@SpringBootApplication
public class CatalogApplication {

    /**
     * Runs Spring Boot application.
     *
     * @param args App arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);
    }
}
