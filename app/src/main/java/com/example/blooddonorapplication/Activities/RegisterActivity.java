package com.example.blooddonorapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.blooddonorapplication.R;
import com.example.blooddonorapplication.Utils.Endpoints;
import com.example.blooddonorapplication.Utils.VolleySingleton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText nameEt, cityEt, phoneEt, blood_groupEt, passwordEt;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        nameEt = findViewById(R.id.name);
        cityEt = findViewById(R.id.city);
        phoneEt = findViewById(R.id.phone_no);
        blood_groupEt = findViewById(R.id.blood_group);
        passwordEt = findViewById(R.id.password);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, city, phone, blood_group, password;
                name = nameEt.getText().toString();
                city = cityEt.getText().toString();
                phone = phoneEt.getText().toString();
                blood_group = blood_groupEt.getText().toString();
                password = passwordEt.getText().toString();
                if (isValid(name, city, phone, blood_group, password)) {
                    register(name, city, phone, blood_group, password);
                }
            }
        });

    }

    private void register(final String name, String city, String phone, String blood_group, String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.REGISTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals("Invalid Credentials")) {
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("city",city).apply();
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    RegisterActivity.this.finish();
                } else {
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("city", city);
                params.put("number", phone);
                params.put("blood_group", blood_group);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean isValid(String name, String city, String phone, String blood_group, String password) {
        List<String> valid_blood_group = new ArrayList<>();
        valid_blood_group.add("A+");
        valid_blood_group.add("A-");
        valid_blood_group.add("B+");
        valid_blood_group.add("B+-");
        valid_blood_group.add("AB+");
        valid_blood_group.add("AB-");
        valid_blood_group.add("O+");
        valid_blood_group.add("O-");

        final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$");

        //Validation for Name
        if (name.isEmpty()) {
            nameEt.setError("Field Can't Be Empty");
            return false;
        } else if (name.length() > 20) {
            nameEt.setError("Username Is Too Long");
            return false;
        }

        //Validation for City
        else if (city.isEmpty()) {
            cityEt.setError("Field Can't Be Empty");
            return false;
        } else if (city.length() > 20) {
            cityEt.setError("City Name Is Too Long");
            return false;
        }

        //Validation for Phone Number
        else if (phone.isEmpty()) {
            phoneEt.setError("Field Can't Be Empty");
            return false;
        } else if (phone.length() != 10) {
            phoneEt.setError("Invalid Number");
            return false;
        }

        //Validation for Blood Group
        else if (blood_group.isEmpty()) {
            blood_groupEt.setError("Field Cannot Be Empty");
            return false;
        } else if (blood_group.length() > 3) {
            blood_groupEt.setError("Only 3 Character Allowed");
            return false;
        } else if (!valid_blood_group.contains(blood_group)) {
            blood_groupEt.setError("Blood Group is Invalid Choose From" + valid_blood_group);
            return false;
        }

        //Validation for Password
        else if (password.isEmpty()) {
            passwordEt.setError("Field Cannot be Empty");
            return false;
        } else if (password.length() < 8) {
            passwordEt.setError("Minimum 8 Character Long");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            passwordEt.setError("Must include 0-9, a-z, A-Z, 1 Special Character ");
            return false;
        }
        return true;
    }

}