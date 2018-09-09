package com.lonelydeveloper97.currenciesmvp.currencies.model.update.impl;

import android.os.Bundle;
import android.util.Log;

import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrenciesDataFactory;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrenciesResponse;

import java.util.Date;
import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;

public class CacheFactoryDecorator implements CurrenciesDataFactory {
    public final static String CACHE_KEY = "CACHE_KEY";

    private final CurrenciesDataFactory currenciesDataFactory;
    @NonNull
    private CurrenciesResponse lastCached;


    public CacheFactoryDecorator(CurrenciesDataFactory currenciesDataFactory, CurrenciesResponse lastCached) {
        this.lastCached = lastCached;
        this.currenciesDataFactory = currenciesDataFactory;
    }

    public CacheFactoryDecorator(CurrenciesDataFactory currenciesDataFactory, Bundle bundle) {
        this(currenciesDataFactory, getCacheFromBundle(bundle));
    }

    private static CurrenciesResponse getCacheFromBundle(Bundle bundle) {
        CurrenciesResponse defaultResponse = new CurrenciesResponse("EUR", new Date().toString(), new HashMap<>());
        if (bundle == null) {
            return defaultResponse;
        }
        CurrenciesResponse currenciesResponse = (CurrenciesResponse) bundle.getSerializable(CACHE_KEY);
        return currenciesResponse == null ? defaultResponse : currenciesResponse;
    }

    @Override
    public Flowable<CurrenciesResponse> create(BaseCurrency baseCurrency) {
        return currenciesDataFactory.create(baseCurrency).doOnNext(r -> lastCached = r)
                .doOnError(e -> Log.e("CacheDecorator", "Error getting updateCurrencies, replacing by cache"))
                .onErrorReturn(throwable -> lastCached.getBase().equals(baseCurrency.getName())
                        ? lastCached : (lastCached = convertLastCache(baseCurrency)));
    }

    public CurrenciesResponse getLastCache() {
        return lastCached;
    }

    private CurrenciesResponse convertLastCache(BaseCurrency newBase) {
        HashMap<String, Double> newRates = new HashMap<>();
        if (validateLastCache(newBase)) {
            double baseRate = lastCached.getRates().get(newBase.getName());
            newRates.put(lastCached.getBase(), 1 / baseRate);
            for (String key : lastCached.getRates().keySet()) {
                if (!key.equals(newBase.getName())) {
                    newRates.put(key, lastCached.getRates().get(key) / baseRate);
                }
            }
        }
        return new CurrenciesResponse(newBase.getName(), new Date().toString(), newRates);
    }

    private boolean validateLastCache(BaseCurrency baseCurrency) {
        return lastCached != null && lastCached.getRates() != null && lastCached.getRates().get(baseCurrency.getName()) != null;
    }
}
