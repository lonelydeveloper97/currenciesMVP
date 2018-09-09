package com.lonelydeveloper97.currenciesmvp.currencies.model.update;


import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyService {
    @GET("/latest")
    Flowable<CurrenciesResponse> getCurrencies(@Query("base") String base);
}
