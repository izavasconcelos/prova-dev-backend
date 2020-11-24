package com.izavasconcelos.desafio.analytics.model;

public class Report {
    private String amountClients;
    private String amountSalesman;
    private String expensiveSale;
    private String worstSalesman;


    public Report(String amountClients, String amountSalesman, String expensiveSale, String worstSalesman) {
        this.amountClients = amountClients;
        this.amountSalesman = amountSalesman;
        this.expensiveSale = expensiveSale;
        this.worstSalesman = worstSalesman;
    }

    @Override
    public String toString() {
        return "Amount Clients:" + amountClients
                + ", Amount Salesman:" + amountSalesman
                + ", Id Most Expensive Sale:" + expensiveSale
                + ", Worst Salesman:" + worstSalesman;
    }
}
