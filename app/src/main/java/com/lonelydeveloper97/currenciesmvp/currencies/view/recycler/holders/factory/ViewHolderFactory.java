package com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders.factory;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface ViewHolderFactory {
    RecyclerView.ViewHolder create(ViewGroup parent, int type);

    interface ViewTypes {
        int BASE = 1;
        int COMMON = 2;
    }
}
