package com.example.blooddonorapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonorapplication.Activities.onBoardScreen.OnBoardingScreen;
import com.example.blooddonorapplication.R;

public class SplashScreen extends AppCompatActivity {

TextView textView;
Animation top_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        textView=findViewById(R.id.text_splash_logo);
        top_anim= AnimationUtils.loadAnimation(this,R.anim.top_animation);

        textView.setAnimation(top_anim);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, OnBoardingScreen.class));
                finish();
            }

        }, 4000);
    }
}