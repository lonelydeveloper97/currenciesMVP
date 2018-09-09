package com.lonelydeveloper97.currenciesmvp.connection.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lonelydeveloper97.currenciesmvp.R;
import com.lonelydeveloper97.currenciesmvp.connection.ConnectionContract;
import com.lonelydeveloper97.currenciesmvp.utills.Anim;

public class ConnectionFragment extends Fragment implements ConnectionContract.View {
    private ConnectionContract.Presenter presenter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.connection_state_header, container, false);
        return view;
    }

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

    @Override
    public void showNoConnectionWindow() {
        if (view.getVisibility() == View.VISIBLE) {
            return;
        }

        int height = getContext().getResources().getDimensionPixelSize(R.dimen.connection_state_height);
        Anim.animateViewHeightChange(view,
                0, height,
                1000,
                () -> view.setVisibility(View.VISIBLE),
                () -> {
                });
    }


    @Override
    public void hideNoConnectionWindow() {
        if (view.getVisibility() == View.GONE) {
            return;
        }

        Anim.animateViewHeightChange(view,
                view.getMeasuredHeight(), 0,
                1000,
                () -> {
                },
                () -> view.setVisibility(View.GONE));

    }

    @Override
    public void setPresenter(ConnectionContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
