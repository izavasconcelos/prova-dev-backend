package com.izavasconcelos.desafio.analytics.controller;

import com.izavasconcelos.desafio.analytics.model.Customer;
import com.izavasconcelos.desafio.analytics.model.Salesman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalyticsController {

    public final static String ID_SALESMAN = "001";
    public final static String ID_CUSTOMER = "002";
    public final static String ID_SALES_DATA = "003";

    private final List<Salesman> salesmanList = new ArrayList<>();
    private final List<Customer> customerList = new ArrayList<>();

    public AnalyticsController() {
    }

    public void getLayout(String data) {
        String [] dataSplit = data.split("ç");

        if(dataSplit[0].equals(ID_SALESMAN)) {
            Salesman salesman = new Salesman(dataSplit[1], dataSplit[2], Double.parseDouble(dataSplit[3]));
            System.out.println(salesman);
            salesmanList.add(salesman);
        } else if(dataSplit[0].equals(ID_CUSTOMER)) {
            Customer customer = new Customer(dataSplit[1], dataSplit[2], dataSplit[3]);
            System.out.println(customer);
            customerList.add(customer);
        } else if(dataSplit[0].equals(ID_SALES_DATA)) {
            Customer customer = new Customer(dataSplit[1], dataSplit[2], dataSplit[3]);
            System.out.println(customer);
            customerList.add(customer);
        }

    }

    public List<Salesman> getSalesmanList() {
        return salesmanList;
    }
}
