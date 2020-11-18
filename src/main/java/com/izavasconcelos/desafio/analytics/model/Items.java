package com.izavasconcelos.desafio.analytics.model;

public class Items {
    private String id;
    private String quantity;
    private String price;

    public Items(String id, String quantity, String price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nId Item: " + id + "  |  Quantity: " + quantity + "  | Price: " + price;
    }
}
