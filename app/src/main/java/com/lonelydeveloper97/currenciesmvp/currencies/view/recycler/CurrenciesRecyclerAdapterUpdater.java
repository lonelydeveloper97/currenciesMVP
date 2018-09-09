package com.lonelydeveloper97.currenciesmvp.currencies.view.recycler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders.BaseCurrencyViewHolder;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders.CurrenciesViewHolder;

class CurrenciesRecyclerAdapterUpdater {
    private MapAdapter<String, Double> currencies;
    @Nullable
    private BaseCurrency base;

    private final CurrenciesRecyclerAdapter adapter;

    CurrenciesRecyclerAdapterUpdater(CurrenciesRecyclerAdapter adapter) {
        this.currencies = new MapAdapter<>();
        this.adapter = adapter;
    }

    public void forceUpdateBase(BaseCurrency currency) {
        if (currency.equals(base)) {
            return;
        }
        base = currency;
        adapter.notifyItemChanged(0);
    }

    public void innerBaseUpdate(BaseCurrency currency) {
        if (currency.equals(base)) {
            return;
        }

        int foundCurrencyPosition = currencies.indexOf(currency.getName());
        if (foundCurrencyPosition != -1) {
            swapBase(currency, foundCurrencyPosition);
        } else {
            base = currency;
        }
    }

    public void updateCurrencies(MapAdapter<String, Double> currencies) {
        this.currencies = currencies;
        adapter.notifyItemRangeChanged(1, getItemSize());
    }

    public void fill(BaseCurrencyViewHolder baseCurrencyViewHolder) {
        if (base != null) {
            baseCurrencyViewHolder.updateBase(base);
        } else {
            baseCurrencyViewHolder.hide();
        }
    }

    public void fill(CurrenciesViewHolder currenciesViewHolder, int position) {
        Pair<String, Double> currencyPair = currencies.get(--position);
        if (currencyPair.first != null && currencyPair.second != null)
            currenciesViewHolder.updateCurrency(currencyPair.first, currencyPair.second);
        else throw new IllegalStateException("Pair can't contain null values");
    }

    public int getItemSize() {
        return currencies.size() + 1;
    }

    private void swapBase(@NonNull BaseCurrency currency, int listPosition) {
        currencies.remove(currency.getName());
        if (base != null) {
            currencies.add(base.getName(), base.getAmount(), listPosition);
        }
        base = currency;

        int viewHolderPosition = listPosition + 1;
        adapter.notifyItemChanged(viewHolderPosition);
        adapter.notifyItemChanged(0);
        adapter.notifyItemMoved(viewHolderPosition, 0);
    }
}
