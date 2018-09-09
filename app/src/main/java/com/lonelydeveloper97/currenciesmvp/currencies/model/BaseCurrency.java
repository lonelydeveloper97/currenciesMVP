package com.lonelydeveloper97.currenciesmvp.currencies.model;

import java.io.Serializable;

public class BaseCurrency implements Serializable {
    private final String name;
    private final double amount;

    public BaseCurrency(String name, double amount) {
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
        return "BaseCurrency{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseCurrency baseCurrency = (BaseCurrency) o;
        return Double.compare(baseCurrency.amount, amount) == 0 &&
                name.equals(baseCurrency.name);
    }

}
