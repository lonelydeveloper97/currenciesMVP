package com.lonelydeveloper97.currenciesmvp.currencies.model.update;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyService {
    @GET("/latest")
    Call<CurenciesResponse> getCurrencies(@Query("base") String base);
}
