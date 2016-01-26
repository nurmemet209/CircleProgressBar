package com.cn.circleprogressbar;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import com.example.library.CircularProgressBarCustom;

public class MainActivity extends AppCompatActivity {

    private Button test;
    private CircularProgressBarCustom circularProgressBarCustom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test=(Button)findViewById(R.id.circle_progress_bar);
        circularProgressBarCustom=(CircularProgressBarCustom)findViewById(R.id.holoCircularProgressBarCustom);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate();
            }
        });

    }

    private void animate(){
        final float progress = (float) (Math.random() * 1);
        int duration = 3000;
        ObjectAnimator animator=ObjectAnimator.ofFloat(circularProgressBarCustom,"progress",progress);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(duration);
        animator.start();
    }
}
