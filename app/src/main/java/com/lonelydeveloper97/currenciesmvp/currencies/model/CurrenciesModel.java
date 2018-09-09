package com.lonelydeveloper97.currenciesmvp.currencies.model;

import java.util.Map;

import io.reactivex.Flowable;

public interface CurrenciesModel {
    BaseCurrency DEFAULT_BASE_CURRENCY = new BaseCurrency("EUR", 100);

    Flowable<Map<String, Double>> currencies();

    BaseCurrency base();

    void updateBase(BaseCurrency base);
}
