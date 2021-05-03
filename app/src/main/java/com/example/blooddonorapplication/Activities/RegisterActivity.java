package com.example.blooddonorapplication.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonorapplication.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText name, city, phone, blood_group, password;
    private Button submit;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

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

    }

    private boolean validateUsername() {
        String nameM = name.getText().toString().trim();

        if (nameM.isEmpty()) {
            name.setError("Field Can't Be Empty");
            return false;
        } else if (nameM.length() > 20) {
            name.setError("Username Is Too Long");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }

    private boolean validateCity() {
        String cityM = city.getText().toString().trim();

        if (cityM.isEmpty()) {
            city.setError("Field Can't Be Empty");
            return false;
        } else if (cityM.length() > 20) {
            city.setError("City Name is Too Long");
            return false;
        } else {
            city.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String Phonem = phone.getText().toString().trim();

        if (Phonem.isEmpty()) {
            phone.setError("Field Can't Be Empty");
            return false;
        } else if (Phonem.length() > 10) {
            phone.setError("Not More Than 10 Digits");
            return false;
        } else {
            phone.setError(null);
            return true;
        }
    }

    private boolean validateBloodGroup() {
        String blood_groupM = blood_group.getText().toString().trim();

        if (blood_groupM.isEmpty()) {
            blood_group.setError("Field Can't Be Empty");
            return false;
        } else if (blood_groupM.length() > 3) {
            blood_group.setError("Only 3 Character Allowed, +/- must Include");
            return false;
        } else {
            blood_group.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {



        String passwordM = password.getText().toString().trim();
        if (passwordM.isEmpty()) {
            password.setError("Field Cannot be Empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordM).matches()) {
            password.setError("Must include 0-9, a-z, A-Z, 1 Special Character ");
            return false;
        } else if (passwordM.length() < 8) {
            password.setError("Minimum 8 Character Long");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public void submit(View view) {
        if (!validateUsername() | !validateCity() | !validatePhone() | !validateBloodGroup() | !validatePassword()) {
            return;
        }
        String input = "Username: " + name.getText().toString();
        input += "\n";
        input += "City: " + city.getText().toString();
        input += "\n";
        input += "Phone: " + phone.getText().toString();
        input += "\n";
        input += "Blood Group: " + blood_group.getText().toString();
        input += "\n";
        input += "Password: " + password.getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}