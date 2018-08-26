package com.lonelydeveloper97.currenciesmvp.currencies.model;

import java.util.List;

import io.reactivex.Observable;

public interface CurrenciesModel {
    Observable<List<Currency>> currencies();

    Currency base();

    void updateBase(Currency base);
}
