package com.izavasconcelos.desafio.analytics.model;

public class Customer {
    private String cnpj;
    private String name;
    private String businessArea;


    public Customer(String cnpj, String name, String businessArea) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String toString() {
        return "\nCNPJ: " + cnpj + "  |  Name: " + name + "  | Business Area: " + businessArea;
    }
}
