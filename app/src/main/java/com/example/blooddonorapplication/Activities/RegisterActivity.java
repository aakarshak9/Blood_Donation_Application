package com.example.blooddonorapplication.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonorapplication.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText name, city, phone, blood_group, password;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        city = findViewById(R.id.city);
        phone = findViewById(R.id.phone_no);
        blood_group = findViewById(R.id.blood_group);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try {
                    login();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void login() {
        String nameM = name.getText().toString().trim();
        String cityM = city.getText().toString().trim();
        String phoneM = phone.getText().toString().trim();
        String bloodM = blood_group.getText().toString().trim();
        String passwordM = password.getText().toString().trim();




    }
}