package com.izavasconcelos.desafio.analytics.service;


import com.izavasconcelos.desafio.analytics.controller.DataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private DataController dataController;

    public ReportService() {}

    public String expensiveSaleId() {
        Map<String, Double> salesList = dataController.getTotalSales();
        salesList = salesList.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x,y)-> {throw new AssertionError();}, LinkedHashMap::new));

        String expensive = salesList.keySet().stream().findFirst().get();
        System.out.println(expensive);
        return expensive;
    }

    public int amountOfCustomers() {
        return dataController.getCustomerList().size();
    }

    public int amountOfSalesman() {
        return dataController.getSalesmanList().size();
    }
}
