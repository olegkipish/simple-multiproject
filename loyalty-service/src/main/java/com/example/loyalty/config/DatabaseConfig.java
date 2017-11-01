package com.example.loyalty.config;

import com.example.loyalty.LoyaltyServiceApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.loyalty.repository")
@EntityScan(basePackageClasses = { LoyaltyServiceApplication.class, Jsr310JpaConverters.class })
public class DatabaseConfig {
}
