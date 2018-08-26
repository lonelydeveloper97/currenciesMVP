package com.lonelydeveloper97.currenciesmvp.generic;

public interface BasePresenter<V extends BaseView> {
    void subscribe(V view);
    void unsubscribe();
}
