package com.izavasconcelos.desafio.analytics.app;

import com.izavasconcelos.desafio.analytics.dao.DataAnalysis;

public class Main {
    public static void main(String[] args) {
        DataAnalysis dataAnalysis = new DataAnalysis();
        boolean bool = dataAnalysis.read();
        if(bool) {
            System.out.println("criou!");
        } else
            System.out.println("nao");
    }
}
