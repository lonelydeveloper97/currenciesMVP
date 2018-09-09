package com.lonelydeveloper97.currenciesmvp.currencies;


import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.MapAdapter;
import com.lonelydeveloper97.currenciesmvp.mvp.BasePresenter;
import com.lonelydeveloper97.currenciesmvp.mvp.BaseView;

public interface CurrenciesContract {
    interface Presenter extends BasePresenter<View> {
        void updateBase(BaseCurrency baseCurrency);
    }

    interface View extends BaseView<Presenter> {
        void onError(Throwable throwable);

        CurrencyDataListener getDataListener();

        interface CurrencyDataListener {
            void onDataUpdated(MapAdapter<String, Double> currencies);

            void updateBaseExternally(BaseCurrency baseCurrency);
        }
    }


}
