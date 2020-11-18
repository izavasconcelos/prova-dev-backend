package com.izavasconcelos.desafio.analytics.app;

import com.izavasconcelos.desafio.analytics.controller.AnalyticsController;
import com.izavasconcelos.desafio.analytics.dao.DataAnalysis;
import com.izavasconcelos.desafio.analytics.model.Salesman;

public class Main {
    public static void main(String[] args) {
        DataAnalysis dataAnalysis = new DataAnalysis();
        boolean bool = dataAnalysis.read();
        if(bool) {
            System.out.println("criou!");
        } else
            System.out.println("nao");

        AnalyticsController list = new AnalyticsController();
        System.out.println(dataAnalysis.totalCustomers());
        dataAnalysis.write();
    }
}
