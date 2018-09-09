package com.lonelydeveloper97.currenciesmvp.utills;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class Fragments {

    @SuppressWarnings("unchecked")
    public static <A extends Fragment> A getFragment(FragmentManager manager, @IdRes int id, A returnIfNotFound) {
        Fragment tasksFragment = manager.findFragmentById(id);
        if (tasksFragment == null) {
            // Create the fragment
            tasksFragment = returnIfNotFound;
        }
        return (A) tasksFragment;
    }

    public static void startFragment(FragmentManager manager, @IdRes int id, Fragment fragment) {
        if(manager.getFragments().contains(fragment)){
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(id, fragment);
        transaction.commit();
    }
}
