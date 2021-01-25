package com.example.weatherapplc;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                    .withFullScreen()
                    .withTargetActivity(MainActivity.class)
                    .withSplashTimeOut(2000)
                    .withBackgroundColor(Color.parseColor("#1a1b29"))
                    .withHeaderText("\n \n \n \n \n \n Little Weather App")
                    .withAfterLogoText("By Andrew M.")
                    .withLogo(R.mipmap.ic_launcher_round);
            config.getHeaderTextView().setTextColor(Color.WHITE);
            config.getAfterLogoTextView().setTextColor(Color.WHITE);
            View easySplashScreen = config.create();
            setContentView(easySplashScreen);
        }
    }

