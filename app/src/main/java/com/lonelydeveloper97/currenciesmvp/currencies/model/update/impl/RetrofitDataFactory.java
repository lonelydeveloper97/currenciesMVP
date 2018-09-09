package com.lonelydeveloper97.currenciesmvp.currencies.model.update.impl;

import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrenciesDataFactory;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrencyService;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurrenciesResponse;


import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataFactory implements CurrenciesDataFactory {
    private static final String HTTPS_REVOLUT = "https://revolut.duckdns.org";
    private static final int PERIOD = 1;
    private final Retrofit retrofit;

    private RetrofitDataFactory(String baseUrl) {
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(baseUrl).build();
    }

    public RetrofitDataFactory() {
        this(HTTPS_REVOLUT);
    }

    @Override
    public Flowable<CurrenciesResponse> create(BaseCurrency baseCurrency) {
        CurrencyService currencyService = retrofit.create(CurrencyService.class);
        return Flowable.interval(PERIOD, TimeUnit.SECONDS).startWith(0L).subscribeOn(Schedulers.single())
                .flatMap(t -> currencyService.getCurrencies(baseCurrency.getName()));
    }
}
