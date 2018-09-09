package com.lonelydeveloper97.currenciesmvp.connection;

import com.lonelydeveloper97.currenciesmvp.mvp.BasePresenter;
import com.lonelydeveloper97.currenciesmvp.mvp.BaseView;

public interface ConnectionContract {
    interface Presenter extends BasePresenter<View> {
    }

    interface View extends BaseView<Presenter> {
        void showNoConnectionWindow();

        void hideNoConnectionWindow();
    }
}
