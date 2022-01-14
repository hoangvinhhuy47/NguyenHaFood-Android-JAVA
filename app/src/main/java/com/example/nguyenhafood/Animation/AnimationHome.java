package com.example.nguyenhafood.Animation;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.nguyenhafood.R;

public class AnimationHome implements View.OnTouchListener {

    private GestureDetector gestureDetector;

    public AnimationHome(Context context, View view) {
        gestureDetector = new GestureDetector(context, new SimpleGestureDectore(view));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public class SimpleGestureDectore extends GestureDetector.SimpleOnGestureListener {
        private View view;
        private boolean finsh = true;

        public SimpleGestureDectore(View view) {
            this.view = view;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (distanceY > 10) {
                hidenview();

            }else if (distanceY < 50 ){
                showview();

            }
            return super.onScroll(e1, e2, distanceX, distanceY);

        }

        private void showview() {
            if (view == null || view.getVisibility() == View.VISIBLE) {

            } else {
                Animation animationup = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_in_show);
                animationup.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        view.setVisibility(View.VISIBLE);
                        finsh = false;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finsh = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                if (finsh == true) {
                    view.startAnimation(animationup);
                }
            }
        }


        private void hidenview() {
            if (view == null || view.getVisibility() == View.GONE) {
                return;
            } else {
                Animation animationdown = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_in_hide);
                animationdown.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        view.setVisibility(View.VISIBLE);
                        finsh = false;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        view.setVisibility(View.GONE);
                        finsh = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                if (finsh == true) {
                    view.startAnimation(animationdown);
                }
            }
        }
    }
}
