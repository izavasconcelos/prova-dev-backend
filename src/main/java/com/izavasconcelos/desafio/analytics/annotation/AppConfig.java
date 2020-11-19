package com.izavasconcelos.desafio.analytics.annotation;

import com.izavasconcelos.desafio.analytics.dao.SalesDAO;
import com.izavasconcelos.desafio.analytics.model.Report;
import com.izavasconcelos.desafio.analytics.controller.DataController;
import com.izavasconcelos.desafio.analytics.service.ReportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.izavasconcelos.desafio.analytics.annotation")
public class AppConfig {

    @Bean
    public DataController dataController() {
        return new DataController();
    }

    @Bean
    public ReportService reportService() {
        return new ReportService();
    }

    @Bean
    public SalesDAO salesDAO() {
        return new SalesDAO();
    }


}
