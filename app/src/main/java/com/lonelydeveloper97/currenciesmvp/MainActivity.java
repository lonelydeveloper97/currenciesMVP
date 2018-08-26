package com.lonelydeveloper97.currenciesmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lonelydeveloper97.currenciesmvp.currencies.model.Currency;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.CurenciesResponse;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Retrofit", "strart");
        RetrofitService retrofitService = new RetrofitService("https://revolut.duckdns.org");
        retrofitService.updateCurrencies(new Currency("EUR",1), new Callback<CurenciesResponse>() {
            @Override
            public void onResponse(Call<CurenciesResponse> call, Response<CurenciesResponse> response) {
                Log.d("answer.rf", response.body().toString());
            }

            @Override
            public void onFailure(Call<CurenciesResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}
