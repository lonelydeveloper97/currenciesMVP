package com.lonelydeveloper97.currenciesmvp.generic;

public interface BaseView<P extends BasePresenter> {
    void setPresenter(P presenter);
}
