package com.lonelydeveloper97.currenciesmvp.currencies.model.update;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public class CurrenciesResponse implements Serializable {
    @SerializedName("base")
    private final String base;
    @SerializedName("date")
    private final String date;
    @SerializedName("rates")
    private final Map<String, Double> rates;

    public CurrenciesResponse(String base, String date, Map<String, Double> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return "CurrenciesResponse{" +
                "base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rate=" + rates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrenciesResponse that = (CurrenciesResponse) o;

        if (base != null ? !base.equals(that.base) : that.base != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return rates != null ? rates.equals(that.rates) : that.rates == null;
    }

    @Override
    public int hashCode() {
        int result = base != null ? base.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (rates != null ? rates.hashCode() : 0);
        return result;
    }
}
