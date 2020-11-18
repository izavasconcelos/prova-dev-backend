package com.izavasconcelos.desafio.analytics.app;

import com.izavasconcelos.desafio.analytics.annotation.AppConfig;
import com.izavasconcelos.desafio.analytics.service.AnalyticsService;
import com.izavasconcelos.desafio.analytics.dao.SalesDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        AnalyticsService analyticsService = (AnalyticsService) appContext.getBean("analyticsController");
        SalesDAO salesDAO = new SalesDAO();
        boolean bool = salesDAO.read();
        if(bool) {
            System.out.println("criou!");
        } else
            System.out.println("nao");

        salesDAO.write();
    }
}
