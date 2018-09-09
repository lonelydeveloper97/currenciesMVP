package com.lonelydeveloper97.currenciesmvp.composite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.lonelydeveloper97.currenciesmvp.R;
import com.lonelydeveloper97.currenciesmvp.connection.ConnectionContract;
import com.lonelydeveloper97.currenciesmvp.connection.presenter.ConnectionPresenterImpl;
import com.lonelydeveloper97.currenciesmvp.connection.view.ConnectionFragment;
import com.lonelydeveloper97.currenciesmvp.currencies.CurrenciesContract;
import com.lonelydeveloper97.currenciesmvp.currencies.model.CurrenciesModel;
import com.lonelydeveloper97.currenciesmvp.currencies.model.impl.CurrenciesModelImpl;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.impl.CacheFactoryDecorator;
import com.lonelydeveloper97.currenciesmvp.currencies.model.update.impl.RetrofitDataFactory;
import com.lonelydeveloper97.currenciesmvp.currencies.presenter.CurrenciesPresenterImpl;
import com.lonelydeveloper97.currenciesmvp.currencies.view.CurrenciesFragment;
import com.lonelydeveloper97.currenciesmvp.utills.Fragments;

import static com.lonelydeveloper97.currenciesmvp.currencies.model.impl.CurrenciesModelImpl.LAST_BASE;

public class MainPageFragment extends Fragment {
    private CacheFactoryDecorator cacheFactoryDecorator;
    private CurrenciesModel currenciesModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_page_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInnerFragments(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(CacheFactoryDecorator.CACHE_KEY, cacheFactoryDecorator.getLastCache());
        outState.putSerializable(LAST_BASE, currenciesModel.base());
        super.onSaveInstanceState(outState);
    }

    private void initInnerFragments(Bundle bundle) {
        initConnect();
        initCurrencies(bundle);
    }

    private void initCurrencies(Bundle bundle) {
        cacheFactoryDecorator = new CacheFactoryDecorator(new RetrofitDataFactory(), bundle);
        currenciesModel = new CurrenciesModelImpl(bundle, cacheFactoryDecorator);

        CurrenciesContract.Presenter presenter = new CurrenciesPresenterImpl(currenciesModel);

        int contentFragment = R.id.content_fragment;

        CurrenciesFragment currenciesFragment = Fragments.getFragment(getChildFragmentManager(), contentFragment, new CurrenciesFragment());
        currenciesFragment.setPresenter(presenter);
        Fragments.startFragment(getChildFragmentManager(), contentFragment, currenciesFragment);
    }

    private void initConnect() {
        ConnectionContract.Presenter presenter = new ConnectionPresenterImpl(ReactiveNetwork.observeInternetConnectivity());

        int connectionFragment = R.id.connection_fragment;

        ConnectionFragment currenciesFragment = Fragments.getFragment(getChildFragmentManager(), connectionFragment, new ConnectionFragment());
        currenciesFragment.setPresenter(presenter);
        Fragments.startFragment(getChildFragmentManager(), connectionFragment, currenciesFragment);
    }
}
