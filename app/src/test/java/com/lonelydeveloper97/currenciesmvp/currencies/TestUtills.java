package com.lonelydeveloper97.currenciesmvp.currencies;

import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrenciesResponse;

import java.util.HashMap;
import java.util.Map;

public class TestUtills {

    public static CurrenciesResponse createMock(String name) {
        HashMap<String, Double> rates = new HashMap<>();
        return createMock(name, rates);
    }

    public static CurrenciesResponse createMock(String name, Map<String, Double> rates) {
        return new CurrenciesResponse(name, "Some date", rates);
    }
}
