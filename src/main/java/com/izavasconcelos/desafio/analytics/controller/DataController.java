package com.izavasconcelos.desafio.analytics.controller;

import com.izavasconcelos.desafio.analytics.dao.SalesDAO;
import com.izavasconcelos.desafio.analytics.model.Customer;
import com.izavasconcelos.desafio.analytics.model.Items;
import com.izavasconcelos.desafio.analytics.model.Sales;
import com.izavasconcelos.desafio.analytics.model.Salesman;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DataController {

    public final static String ID_SALESMAN = "001";
    public final static String ID_CUSTOMER = "002";
    public final static String ID_SALES_DATA = "003";

    private List<Salesman> salesmanList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private List<Sales> salesList;
    private final List<Items> itemsList;
    private final Map<String, Double> totalSales = new HashMap<>();
    private double total = 0;

    private SalesDAO salesDAO;


    public DataController() {
        salesList = new ArrayList<>();
        itemsList = new ArrayList<>();
        salesDAO = new SalesDAO();
    }

    public void extractInfoDataFile() {
        List<String> dataList = salesDAO.getDataList();

        for (String list: dataList) {
            getLayout(list);
        }
        System.out.println(dataList.get(0));
    }

    public void getLayout(String data) {

        String [] dataSplit = data.split("ç");

        switch (dataSplit[0]) {
            case ID_SALESMAN:
                Salesman salesman = new Salesman(dataSplit[1], dataSplit[2], Double.parseDouble(dataSplit[3]));
                salesmanList.add(salesman);
                break;
            case ID_CUSTOMER: {
                Customer customer = new Customer(dataSplit[1], dataSplit[2], dataSplit[3]);
                customerList.add(customer);
                break;
            }
            case ID_SALES_DATA: {
                salesItemsSeparated(dataSplit[1], dataSplit[2]);

                Sales sales = new Sales(dataSplit[1], itemsList, dataSplit[3]);
                salesList.add(sales);
                break;
            }
        }
    }

    public List<Items> salesItemsSeparated(String saleId, String allItems) {
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
        totalSales.put(saleId,total);
        return itemsList;
    }

    public List<Salesman> getSalesmanList() {
        return salesmanList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Sales> getSalesList() {
        return salesList;
    }

    public Map<String, Double> getTotalSales() {
        return totalSales;
    }
}
