package com.lonelydeveloper97.currenciesmvp.connection.presenter;

import com.lonelydeveloper97.currenciesmvp.connection.ConnectionContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ConnectionPresenterImpl implements ConnectionContract.Presenter {
    private final Observable<Boolean> internetConnection;
    private Disposable subscription;

    public ConnectionPresenterImpl(Observable<Boolean> internetConnection) {
        this.internetConnection = internetConnection;
    }

    @Override
    public void subscribe(ConnectionContract.View view) {
        subscription = internetConnection.observeOn(AndroidSchedulers.mainThread()).subscribe(r -> {
            if (r) {
                view.hideNoConnectionWindow();
            } else {
                view.showNoConnectionWindow();
            }
        }, throwable -> {
            //ignore
        });
    }

    @Override
    public void unsubscribe() {
        subscription.dispose();
    }
}
