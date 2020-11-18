package com.izavasconcelos.desafio.analytics.annotation;

import com.izavasconcelos.desafio.analytics.service.AnalyticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.izavasconcelos.desafio.analytics.annotation")
public class AppConfig {

    @Bean
    public AnalyticsService analyticsController() {
        return new AnalyticsService();
    }
}
