package com.lonelydeveloper97.currenciesmvp.currencies.model.impl;

import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrenciesResponse;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.functions.BiFunction;

public class ResponseToCurrenciesConverter implements BiFunction<BaseCurrency, Flowable<CurrenciesResponse>, Flowable<Map<String, Double>>> {
    @Override
    public Flowable<Map<String, Double>> apply(BaseCurrency baseCurrency, Flowable<CurrenciesResponse> currenciesResponseFlowable) {
        return currenciesResponseFlowable.map(r -> createFromResponse(baseCurrency, r));
    }

    private Map<String, Double> createFromResponse(BaseCurrency base, CurrenciesResponse response) {
        Map<String, Double> result = response.getRates();
        for (String key : result.keySet()) {
            result.put(key, result.get(key) * base.getAmount());
        }
        return result;
    }
}
