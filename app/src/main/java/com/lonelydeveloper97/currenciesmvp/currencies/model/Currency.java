package com.lonelydeveloper97.currenciesmvp.currencies.model;

public class Currency {
    private String name;
    private double amount;


    public Currency(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
