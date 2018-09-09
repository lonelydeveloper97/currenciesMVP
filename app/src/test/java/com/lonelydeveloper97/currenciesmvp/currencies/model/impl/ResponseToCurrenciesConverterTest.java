package com.lonelydeveloper97.currenciesmvp.currencies.model.impl;

import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.model.impl.ResponseToCurrenciesConverter;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrenciesResponse;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;

public class ResponseToCurrenciesConverterTest {

    @Test
    public void apply() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 0.1);
        rates.put("RUB", 99.0);
        rates.put("GBP", 0.5);
        CurrenciesResponse response = new CurrenciesResponse("EUR", "01.01.01", rates);
        ResponseToCurrenciesConverter responseToCurrenciesConverter = new ResponseToCurrenciesConverter();

        Map<String, Double> currencies = responseToCurrenciesConverter.apply(new BaseCurrency("EUR", 100), Flowable.just(response)).test().values().get(0);

        Assert.assertEquals(3, currencies.size());
        Assert.assertEquals(50, currencies.get("GBP"), 0);
        Assert.assertEquals(10, currencies.get("USD"), 0);
        Assert.assertEquals(9900, currencies.get("RUB"), 0);
    }
}