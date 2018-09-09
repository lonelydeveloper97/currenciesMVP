package com.lonelydeveloper97.currenciesmvp.currencies.view.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lonelydeveloper97.currenciesmvp.currencies.CurrenciesContract;
import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders.BaseCurrencyViewHolder;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders.CurrenciesViewHolder;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders.factory.ViewHolderFactory;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders.factory.ViewHolderFactoryImpl;

import java9.util.function.Consumer;

public class CurrenciesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CurrenciesContract.View.CurrencyDataListener {
    private final ViewHolderFactory viewHolderFactory;
    private final CurrenciesRecyclerAdapterUpdater updater;

    public CurrenciesRecyclerAdapter(Consumer<BaseCurrency> onChangeConsumer) {
        this.updater = new CurrenciesRecyclerAdapterUpdater(this);
        Consumer<BaseCurrency> onChangeConsumerDecorator = (currency) -> {
            updater.innerBaseUpdate(currency);
            onChangeConsumer.accept(currency);
        };
        this.viewHolderFactory = new ViewHolderFactoryImpl(onChangeConsumerDecorator);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return viewHolderFactory.create(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case ViewHolderFactory.ViewTypes.BASE:
                updater.fill((BaseCurrencyViewHolder) viewHolder);
                break;
            case ViewHolderFactory.ViewTypes.COMMON:
                updater.fill((CurrenciesViewHolder) viewHolder, i);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ViewHolderFactory.ViewTypes.BASE : ViewHolderFactory.ViewTypes.COMMON;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return updater.getItemSize();
    }

    @Override
    public void onDataUpdated(MapAdapter<String, Double> currencies) {
        updater.updateCurrencies(currencies);
    }

    @Override
    public void updateBaseExternally(BaseCurrency baseCurrency) {
        updater.forceUpdateBase(baseCurrency);
    }
}
