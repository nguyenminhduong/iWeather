package com.example.framgia.iweather.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.framgia.iweather.R;

/**
 * Created by chaupham on 11/24/2016.
 */
public class SplashActivity extends AppCompatActivity {
    private final int TIME_DELAY = 2000;
    private Handler mHandlerSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandlerSplash = new Handler();
        mHandlerSplash.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, TIME_DELAY);
    }
}
