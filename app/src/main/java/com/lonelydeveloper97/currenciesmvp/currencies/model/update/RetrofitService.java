package com.lonelydeveloper97.currenciesmvp.currencies.model.update;

import com.lonelydeveloper97.currenciesmvp.currencies.model.Currency;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;

    public RetrofitService(String baseUrl) {
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).build();
    }

    public void updateCurrencies(Currency base, Callback<CurenciesResponse> callback) {
        retrofit.create(CurrencyService.class).getCurrencies(base.getName()).enqueue(callback);
    }
}
