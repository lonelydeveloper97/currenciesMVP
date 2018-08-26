package com.lonelydeveloper97.currenciesmvp.currencies.model.update;

import java.util.Map;

public class CurenciesResponse {
    private final String base;
    private final String date;
    protected final Map<String, String> rates;

    public CurenciesResponse(String base, String date, Map<String, String> rates) {
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

    public Map<String, String> getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return "CurenciesResponse{" +
                "base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rate=" + rates +
                '}';
    }
}
