package com.lonelydeveloper97.currenciesmvp.mvp;

public interface BasePresenter<V extends BaseView> {
    void subscribe(V view);
    void unsubscribe();
}
