package com.izavasconcelos.desafio.analytics.model;

public class Salesman {
    private String cpf;
    private String name;
    private double salary;

    public Salesman(String cpf, String name, double salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "\nCpf: " + cpf + "  |  Name: " + name + "  | Salary: " + salary;
    }
}
