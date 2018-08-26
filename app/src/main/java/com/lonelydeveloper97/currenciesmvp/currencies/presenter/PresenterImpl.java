package com.lonelydeveloper97.currenciesmvp.currencies.presenter;

import com.lonelydeveloper97.currenciesmvp.currencies.model.CurrenciesModel;
import com.lonelydeveloper97.currenciesmvp.currencies.model.Currency;

import java.util.List;

import io.reactivex.functions.Consumer;

public class PresenterImpl implements CurenciesContract.Presenter {
    private CurrenciesModel currenciesModel;

    public PresenterImpl(CurrenciesModel currenciesModel) {
        this.currenciesModel = currenciesModel;
    }

    @Override
    public void updateBase(Currency currency) {
        currenciesModel.updateBase(currency);
    }

    @Override
    public void subscribe(final CurenciesContract.View view) {
        currenciesModel.currencies()
                .doOnError(throwable -> view.onError())
                .subscribe(currencies -> view.onDataUpdated(currenciesModel.base(), currencies)).dispose();
    }

    @Override
    public void unsubscribe() {

    }
}
