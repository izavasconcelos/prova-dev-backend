package com.izavasconcelos.desafio.analytics.app;

import com.izavasconcelos.desafio.analytics.annotation.AppConfig;
import com.izavasconcelos.desafio.analytics.controller.DataController;
import com.izavasconcelos.desafio.analytics.service.ReportService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        DataController dataController = (DataController) appContext.getBean("dataController");
        ReportService service = (ReportService) appContext.getBean("reportService");

        if(true) {
            System.out.println("criou!");
        } else
            System.out.println("nao");
        dataController.extractInfoDataFile();
        String i = service.expensiveSaleId();
        System.out.println(service.amountOfCustomers());
        System.out.println(service.amountOfSalesman());
        service.getSalesList();
    }
}
