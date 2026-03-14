package com.bannerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the BannerApp console application.
 *
 * <p>Spring Boot scans all components under {@code com.bannerapp},
 * builds the application context, then calls every registered
 * {@link org.springframework.boot.CommandLineRunner} bean — in this
 * project that is {@link com.bannerapp.runner.BannerRunner}.
 *
 * <p>Usage:
 * <pre>
 *   java -jar BannerApp.jar "Hello World"
 * </pre>
 */
@SpringBootApplication
public class BannerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BannerAppApplication.class, args);
    }
}
