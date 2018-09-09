package com.lonelydeveloper97.currenciesmvp.currencies.model.update;

import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;

import io.reactivex.Flowable;

public interface CurrenciesDataFactory {
    Flowable<CurrenciesResponse> create(BaseCurrency baseCurrency);
}
