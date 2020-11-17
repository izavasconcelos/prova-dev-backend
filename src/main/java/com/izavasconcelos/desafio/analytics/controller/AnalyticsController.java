package com.izavasconcelos.desafio.analytics.controller;

import java.util.Arrays;

public class AnalyticsController {

    public final static String ID_SALESMAN = "001";
    public final static String ID_CUSTOMER = "002";
    public final static String ID_SALES_DATA = "003";

    public void getLayout(String data) {
        String [] dataSplit = data.split("ç");
        System.out.println(Arrays.toString(dataSplit));
    }
}
