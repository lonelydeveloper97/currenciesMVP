package com.lonelydeveloper97.currenciesmvp.currencies.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lonelydeveloper97.currenciesmvp.R;
import com.lonelydeveloper97.currenciesmvp.currencies.CurrenciesContract;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.CacheRecyclerViewUpdater;
import com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.CurrenciesRecyclerAdapter;


public class CurrenciesFragment extends Fragment implements CurrenciesContract.View {
    private CurrenciesContract.Presenter presenter;
    private CurrenciesContract.View.CurrencyDataListener listener;

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.currencies_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        CurrenciesRecyclerAdapter adapter = new CurrenciesRecyclerAdapter(currency -> {
            presenter.updateBase(currency);
            recyclerView.scrollToPosition(0);
        });
        adapter.setHasStableIds(true);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listener = new CacheRecyclerViewUpdater(adapter, recyclerView);
    }

    @Override
    public void setPresenter(CurrenciesContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("error", throwable.getMessage());
    }

    @Override
    public CurrencyDataListener getDataListener() {
        return listener;
    }
}
