package com.lonelydeveloper97.currenciesmvp.mvp;


public interface BaseView<P extends BasePresenter> {
    void setPresenter(P presenter);
}
