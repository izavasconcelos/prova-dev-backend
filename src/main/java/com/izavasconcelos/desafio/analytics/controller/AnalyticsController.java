package com.izavasconcelos.desafio.analytics.controller;

import com.izavasconcelos.desafio.analytics.model.Customer;
import com.izavasconcelos.desafio.analytics.model.Items;
import com.izavasconcelos.desafio.analytics.model.Sales;
import com.izavasconcelos.desafio.analytics.model.Salesman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalyticsController {

    public final static String ID_SALESMAN = "001";
    public final static String ID_CUSTOMER = "002";
    public final static String ID_SALES_DATA = "003";

    private List<Salesman> salesmanList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private List<Sales> salesList;
    private List<Items> itemsList;
    private int countCustomers = 0;
    private int countSalesman = 0;
    private double expensiveSale = 0;
    private String saleId;

    public AnalyticsController() {
        salesList = new ArrayList<>();
        itemsList = new ArrayList<>();
    }

    public void getLayout(String data) {
        String [] dataSplit = data.split("ç");

        switch (dataSplit[0]) {
            case ID_SALESMAN:
                countSalesman++;
                Salesman salesman = new Salesman(dataSplit[1], dataSplit[2], Double.parseDouble(dataSplit[3]));
                salesmanList.add(salesman);
                break;
            case ID_CUSTOMER: {
                countCustomers++;
                break;
            }
            case ID_SALES_DATA: {
                String [] items = dataSplit[2].split(",|\\s|]"); //\\s|]|,\\s|\\[");
                items[0]= items[0].substring(1);
                double total = 0;
                for(int i=0; i < items.length; i++) {
                    String [] item = items[i].split("-");
                    double quantity = Double.parseDouble(item[1]);
                    double price = Double.parseDouble(item[2]);

                    total = total + (quantity * price);
                    Items listItems = new Items(item[0], item[1], item[2]);
                    itemsList.add(listItems);
                }
                System.out.println(total);
                if(total > expensiveSale) {
                    expensiveSale = total;
                    saleId = dataSplit[1];
                }
                Sales sales = new Sales(dataSplit[1], itemsList, dataSplit[3]);
                salesList.add(sales);
                //System.out.println(salesList);
                break;
            }
        }
    }

    public List<Salesman> getSalesmanList() {
        return salesmanList;
    }

    public int getCountCustomers() {
        return countCustomers;
    }

    public int getCountSalesman() {
        return countSalesman;
    }

    public String getExpensiveSaleId() {
        return saleId;
    }
}
