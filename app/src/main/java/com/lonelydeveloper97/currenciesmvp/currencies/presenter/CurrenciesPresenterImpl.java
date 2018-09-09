package com.lonelydeveloper97.currenciesmvp.currencies.presenter;

import com.lonelydeveloper97.currenciesmvp.currencies.CurrenciesContract;
import com.lonelydeveloper97.currenciesmvp.currencies.model.CurrenciesModel;
import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.MapAdapter;


import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CurrenciesPresenterImpl implements CurrenciesContract.Presenter {
    private final CurrenciesModel currenciesModel;
    private final MapAdapter<String, Double> mapAdapter;

    private Disposable subscription;
    private CurrenciesContract.View current;

    public CurrenciesPresenterImpl(CurrenciesModel currenciesModel) {
        this.currenciesModel = currenciesModel;
        this.mapAdapter = new MapAdapter<>();
    }

    @Override
    public void updateBase(BaseCurrency baseCurrency) {
        currenciesModel.updateBase(baseCurrency);
        unsubscribe();
        subscribe(current);
    }

    @Override
    public void subscribe(final CurrenciesContract.View view) {
        current = view;

        view.getDataListener().updateBaseExternally(currenciesModel.base());

        Flowable<Map<String, Double>> flowable = currenciesModel.currencies();
        subscription = flowable
                .map(currencies -> {
                    mapAdapter.updateValuesMap(currencies);
                    return mapAdapter;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view.getDataListener()::onDataUpdated, view::onError);
    }

    @Override
    public void unsubscribe() {
        if (subscription == null)
            return;
        subscription.dispose();
    }
}
