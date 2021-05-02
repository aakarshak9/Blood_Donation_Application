package com.example.blooddonorapplication.Activities.onBoardScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.blooddonorapplication.Activities.MainActivity;
import com.example.blooddonorapplication.Activities.RegisterActivity;
import com.example.blooddonorapplication.R;

public class OnBoardingScreen extends AppCompatActivity {

    public static ViewPager viewPager;
    SlideViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        viewPager = findViewById(R.id.viewpager);
        adapter = new SlideViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        if (isOpenAlready()) {
            Intent intent = new Intent(OnBoardingScreen.this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            SharedPreferences.Editor editor = getSharedPreferences("OnBoard", MODE_PRIVATE).edit();
            editor.putBoolean("OnBoard", true);
            editor.commit();
        }
    }

    private boolean isOpenAlready() {

        SharedPreferences sharedPreferences = getSharedPreferences("OnBoard", MODE_PRIVATE);
        boolean result = sharedPreferences.getBoolean("OnBoard", false);
        return result;
    }
}

