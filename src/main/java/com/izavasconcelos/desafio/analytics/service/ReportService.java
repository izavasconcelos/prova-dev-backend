package com.izavasconcelos.desafio.analytics.service;


import com.izavasconcelos.desafio.analytics.controller.DataController;
import com.izavasconcelos.desafio.analytics.model.Report;
import com.izavasconcelos.desafio.analytics.model.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private DataController dataController;

    public ReportService() {
    }

    public String reportDataAnalysis() {
        List<Report> report= new ArrayList<>();
        report.add(new Report(String.valueOf(amountOfCustomers()),
                String.valueOf(amountOfSalesman()),
                expensiveSaleId(),
                getWorstSalesman()));
        return report.toString();
    }

    public String expensiveSaleId() {
        Map<String, Double> totalSales = dataController.getTotalSales();
        totalSales = totalSales.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x,y)-> {throw new AssertionError();}, LinkedHashMap::new));

        return totalSales.keySet().stream().findFirst().get();
    }

    public int amountOfCustomers() {
        return dataController.getCustomerList().size();
    }

    public int amountOfSalesman() {
        return dataController.getSalesmanList().size();
    }

    public List<Sales> getSalesList() {
        return dataController.getSalesList();
    }

    public String getWorstSalesman() {
        Map<String, Double> salesmanTotalSales = dataController.getTotalSalesman();

        salesmanTotalSales = salesmanTotalSales.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x,y)-> {throw new AssertionError();}, LinkedHashMap::new
                ));
        return salesmanTotalSales.keySet().stream().findFirst().get();
    }
}
