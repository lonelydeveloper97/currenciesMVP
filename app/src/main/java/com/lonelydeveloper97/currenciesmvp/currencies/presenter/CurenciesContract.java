package com.lonelydeveloper97.currenciesmvp.currencies.presenter;


import com.lonelydeveloper97.currenciesmvp.currencies.model.Currency;
import com.lonelydeveloper97.currenciesmvp.generic.BasePresenter;
import com.lonelydeveloper97.currenciesmvp.generic.BaseView;

import java.util.List;

public interface CurenciesContract {
    interface Presenter extends BasePresenter<View> {
        void updateBase(Currency currency);
    }

    interface View extends BaseView<Presenter>{
        void onDataUpdated(Currency base, List<Currency> currencies);

        void onError();
    }
}
