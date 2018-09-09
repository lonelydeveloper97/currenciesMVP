package com.lonelydeveloper97.currenciesmvp.currencies.view.recycler;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.lonelydeveloper97.currenciesmvp.currencies.CurrenciesContract;
import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;

import java.util.concurrent.atomic.AtomicBoolean;


public class CacheRecyclerViewUpdater implements CurrenciesContract.View.CurrencyDataListener {
    private MapAdapter<String, Double> currencies;

    private final CurrenciesRecyclerAdapter currenciesRecyclerAdapter;
    private final RecyclerView recyclerView;

    private final AtomicBoolean updated = new AtomicBoolean();

    private final AtomicBoolean scrolling = new AtomicBoolean();

    private static final Handler handler = new Handler();
    private final Runnable updateRunnable;

    public CacheRecyclerViewUpdater(CurrenciesRecyclerAdapter currenciesRecyclerAdapter, RecyclerView recyclerView) {
        this.currenciesRecyclerAdapter = currenciesRecyclerAdapter;
        this.recyclerView = recyclerView;
        updateRunnable = this::dispatchUpdate;
        recyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public void onAnimationFinished(@NonNull RecyclerView.ViewHolder viewHolder) {
                super.onAnimationFinished(viewHolder);
                handler.post(updateRunnable);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        scrolling.set(true);
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        scrolling.set(false);
                        handler.post(updateRunnable);
                        break;
                }
            }
        });
    }

    private void dispatchUpdate() {
        if (updated.get() && !recyclerView.isAnimating() && !scrolling.get()) {
            currenciesRecyclerAdapter.onDataUpdated(currencies);
        }else {
            updated.set(true);
        }
    }

    @Override
    public void onDataUpdated(MapAdapter<String, Double> currencies) {
        this.currencies = currencies;
        dispatchUpdate();
    }

    @Override
    public void updateBaseExternally(BaseCurrency baseCurrency) {
        currenciesRecyclerAdapter.updateBaseExternally(baseCurrency);
    }
}
