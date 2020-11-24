package com.izavasconcelos.desafio.analytics.service;

import com.izavasconcelos.desafio.analytics.controller.DataController;
import com.izavasconcelos.desafio.analytics.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private DataController dataController;

    public ReportService() {
    }

    public String reportDataAnalysis() {
        Report report = new Report(String.valueOf(amountOfCustomers()),
                String.valueOf(amountOfSalesman()),
                expensiveSaleId(),
                getWorstSalesman());
        return report.toString();
    }

    public String expensiveSaleId() {
        Map<String, Double> totalSales = dataController.getTotalSalesById();
        if(totalSales.isEmpty()){
            return "none";
        }
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

    public String getWorstSalesman() {
        Map<String, Double> salesmanTotalSales = dataController.getTotalSalesBySalesman();

        if(salesmanTotalSales.isEmpty()) {
            return "none";
        }
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
