package com.izavasconcelos.desafio.analytics.controller;

import com.izavasconcelos.desafio.analytics.dao.SalesDAO;
import com.izavasconcelos.desafio.analytics.model.Customer;
import com.izavasconcelos.desafio.analytics.model.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class DataController {

    public final static String ID_SALESMAN = "001";
    public final static String ID_CUSTOMER = "002";
    public final static String ID_SALES_DATA = "003";
    public final static Logger logger = LoggerFactory.getLogger(DataController.class);

    private List<Salesman> salesmanList;
    private List<Customer> customerList;
    private Map<String, Double> totalSalesById;
    private Map<String, Double> totalSalesBySalesman;


    @Autowired
    private SalesDAO salesDAO;

    public DataController() {
        salesmanList = new ArrayList<>();
        customerList = new ArrayList<>();
        totalSalesById = new HashMap<>();
        totalSalesBySalesman = new HashMap<>();
    }

    public void executeDataAnalysis() {
        List<String> dataList = salesDAO.getDataList();
        dataList.forEach(this::getLayout);
        dataList.clear();

        salesDAO.writeDataAnalysisOutputFile();
        customerList.clear();
        salesmanList.clear();
        totalSalesById.clear();
        totalSalesBySalesman.clear();
    }

    public void getLayout(String data) {

        String [] dataSplit = data.split("ç");

        switch (dataSplit[0]) {
            case ID_SALESMAN:
                Salesman salesman = new Salesman(dataSplit[1], dataSplit[2], Double.parseDouble(dataSplit[3]));
                if(salesmanList.stream().noneMatch(cpf -> cpf.getCpf().equals(dataSplit[1]))) {
                    salesmanList.add(salesman);
                } else {
                    logger.info("Vendedor já existe!");
                }
                break;
            case ID_CUSTOMER: {
                Customer customer = new Customer(dataSplit[1], dataSplit[2], dataSplit[3]);
                if(customerList.stream().noneMatch(cnpj -> cnpj.getCnpj().equals(dataSplit[1]))) {
                    customerList.add(customer);
                } else {
                    logger.debug("Cliente já existe!");
                }
                break;
            }
            case ID_SALES_DATA: {
                salesItemsSeparated(dataSplit[1], dataSplit[2], dataSplit[3]);
                break;
            }
        }
    }

    public void salesItemsSeparated(String saleId, String listAllItems, String name) {
        String [] items = listAllItems.split(",|\\s|]");
        items[0]= items[0].substring(1);

        double total = 0;
        for (String separateItems : items) {
            String[] item = separateItems.split("-");
            double quantity = Double.parseDouble(item[1]);
            double price = Double.parseDouble(item[2]);
            total = total + (quantity * price);
        }
        if(!totalSalesById.containsKey(saleId)){
            totalSalesById.put(saleId,total);
        } else {
            logger.debug("Id da Venda duplicado!");
        }

        if(totalSalesBySalesman.containsKey(name)){
            total += totalSalesBySalesman.get(name);
        }
        totalSalesBySalesman.put(name, total);
    }

    public List<Salesman> getSalesmanList() {
        return salesmanList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Map<String, Double> getTotalSalesById() {
        return totalSalesById;
    }

    public Map<String, Double> getTotalSalesBySalesman() {
        return totalSalesBySalesman;
    }
}
