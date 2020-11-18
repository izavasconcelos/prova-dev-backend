package com.izavasconcelos.desafio.analytics.model;

import java.util.List;

public class Sales {
    private String saleId;
    private List<Items> salesList;
    private String name;

    public Sales(String saleId, List<Items> salesList, String name) {
        this.saleId = saleId;
        this.salesList = salesList;
        this.name = name;
    }

    public String getSaleId() {
        return saleId;
    }

    @Override
    public String toString() {
        return "\n\n\nSales ID: " + saleId + "  |  Items: " + salesList + "  | Salesman: " + name;
    }
}
