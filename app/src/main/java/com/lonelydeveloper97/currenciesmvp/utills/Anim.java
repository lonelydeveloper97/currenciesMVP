package com.lonelydeveloper97.currenciesmvp.utills;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public class Anim {

    public static void animateViewHeightChange(View view, int from, int to, int duration, Runnable onStart, Runnable onStop){
        ValueAnimator anim = ValueAnimator.ofInt(from, to);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = val;
            view.setLayoutParams(layoutParams);
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                onStart.run();
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                onStop.run();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        anim.setDuration(duration);
        anim.start();
    }
}
