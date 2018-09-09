package com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders.factory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lonelydeveloper97.currenciesmvp.R;
import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders.BaseCurrencyViewHolder;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders.CurrenciesViewHolder;

import java9.util.function.Consumer;

public class ViewHolderFactoryImpl implements ViewHolderFactory {
    private final Consumer<BaseCurrency> baseCurrencyConsumer;
    private LayoutInflater layoutInflater;

    public ViewHolderFactoryImpl(Consumer<BaseCurrency> baseCurrencyConsumer) {
        this.baseCurrencyConsumer = baseCurrencyConsumer;
    }

    @Override
    public RecyclerView.ViewHolder create(ViewGroup parent, int type) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.currency_row, parent, false);

        switch (type) {
            case ViewTypes.BASE:
                return new BaseCurrencyViewHolder(view, baseCurrencyConsumer);
            case ViewTypes.COMMON:
                return new CurrenciesViewHolder(view, baseCurrencyConsumer);
        }
        throw new IllegalArgumentException("View type not supported");
    }
}
