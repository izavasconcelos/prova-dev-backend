package com.izavasconcelos.desafio.analytics.service;

import com.izavasconcelos.desafio.analytics.model.Customer;
import com.izavasconcelos.desafio.analytics.model.Items;
import com.izavasconcelos.desafio.analytics.model.Sales;
import com.izavasconcelos.desafio.analytics.model.Salesman;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalyticsService {

    public final static String ID_SALESMAN = "001";
    public final static String ID_CUSTOMER = "002";
    public final static String ID_SALES_DATA = "003";

    private List<Salesman> salesmanList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private List<Sales> salesList;
    private List<Items> itemsList;
    private Map<String, Double> expensive = new HashMap<>();
    private int countCustomers = 0;
    private int countSalesman = 0;
    private double expensiveSale = 0;
    private double total = 0;

    public AnalyticsService() {
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
                salesItemsSeparated(dataSplit[2]);
                if(total > expensiveSale) {
                    expensive.put(dataSplit[1],total);
                    expensiveSale = total;
                }
                Sales sales = new Sales(dataSplit[1], itemsList, dataSplit[3]);
                salesList.add(sales);
                break;
            }
        }
    }

    public List<Items> salesItemsSeparated(String allItems) {
        String [] items = allItems.split(",|\\s|]"); //\\s|]|,\\s|\\[");
        items[0]= items[0].substring(1);
        total = 0;
        for(int i=0; i < items.length; i++) {
            String [] item = items[i].split("-");
            double quantity = Double.parseDouble(item[1]);
            double price = Double.parseDouble(item[2]);

            total = total + (quantity * price);
            Items listItems = new Items(item[0], item[1], item[2]);
            itemsList.add(listItems);
        }
        return itemsList;
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

    public Map<String, Double> getExpensiveSaleId() {
        return expensive;
    }
}
