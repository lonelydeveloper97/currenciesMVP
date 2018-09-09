package com.lonelydeveloper97.currenciesmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lonelydeveloper97.currenciesmvp.composite.MainPageFragment;
import com.lonelydeveloper97.currenciesmvp.utills.Fragments;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainPageFragment fragment = Fragments.getFragment(getSupportFragmentManager(), R.id.contentFrame, new MainPageFragment());
        Fragments.startFragment(getSupportFragmentManager(), R.id.contentFrame, fragment);
    }
}
