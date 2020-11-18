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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nCpf: " + cpf + "  |  Name: " + name + "  | Salary: " + salary;
    }
}
