package com.lonelydeveloper97.currenciesmvp.currencies.model.impl;

import android.os.Bundle;

import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.model.CurrenciesModel;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrenciesDataFactory;

import java.util.Map;

import io.reactivex.Flowable;

public class CurrenciesModelImpl implements CurrenciesModel {
    public static final String LAST_BASE = "LastBase";

    private BaseCurrency base;
    private final CurrenciesDataFactory currenciesDataFactory;
    private final ResponseToCurrenciesConverter converter;
    private Flowable<Map<String, Double>> currencies;

    public CurrenciesModelImpl(BaseCurrency base, CurrenciesDataFactory currenciesDataFactory) {
        this.currenciesDataFactory = currenciesDataFactory;
        this.converter = new ResponseToCurrenciesConverter();
        updateBase(base);
    }

    public CurrenciesModelImpl(Bundle bundle, CurrenciesDataFactory currenciesDataFactory) {
        this.currenciesDataFactory = currenciesDataFactory;
        this.converter = new ResponseToCurrenciesConverter();
        updateBase(get(bundle));
    }

    private BaseCurrency get(Bundle bundle) {
        if (bundle == null) {
            return DEFAULT_BASE_CURRENCY;
        }

        BaseCurrency initialCurrency = (BaseCurrency) bundle.get(LAST_BASE);
        if (initialCurrency == null) {
            return DEFAULT_BASE_CURRENCY;
        } else {
            return initialCurrency;
        }
    }

    @Override
    public Flowable<Map<String, Double>> currencies() {
        return currencies;
    }

    @Override
    public BaseCurrency base() {
        return base;
    }

    @Override
    public void updateBase(BaseCurrency base) {
        this.base = base;
        currencies = converter.apply(base, currenciesDataFactory.create(base));
    }
}
