package com.lonelydeveloper97.currenciesmvp.currencies.model.impl;

import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.model.CurrenciesModel;
import com.lonelydeveloper97.currenciesmvp.currencies.model.impl.CurrenciesModelImpl;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrenciesResponse;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;


public class CurrenciesModelImplTest {

    @Test
    public void updateBase() {
        BaseCurrency eur = new BaseCurrency("EUR", 100);
        BaseCurrency usd = new BaseCurrency("USD", 100);

        CurrenciesModel currenciesModel = new CurrenciesModelImpl(eur, currency -> {
            CurrenciesResponse currenciesResponse = null;
            if (currency == eur) {
                Map<String, Double> rates = new HashMap<>();
                rates.put("USD", 0.1);
                rates.put("RUB", 99.0);
                rates.put("GBP", 0.5);
                currenciesResponse = new CurrenciesResponse("EUR", "01.01.01", rates);
            } else if (currency == usd) {
                Map<String, Double> rates = new HashMap<>();
                rates.put("EUR", 10.0);
                rates.put("RUB", 99.0);
                rates.put("GBP", 0.5);
                currenciesResponse = new CurrenciesResponse("EUR", "01.01.01", rates);
            }
            return Flowable.just(currenciesResponse);
        });

        currenciesModel.updateBase(usd);

        Assert.assertEquals(true, currenciesModel.currencies().test().values().get(0).get("EUR").equals(1000.0));
    }
}