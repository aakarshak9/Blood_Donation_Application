package com.example.blooddonorapplication.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.blooddonorapplication.R;
import com.google.android.material.textfield.TextInputEditText;

public class MakeRequestActivity extends AppCompatActivity {

    TextInputEditText messagetext;
    TextView chooseImageText;
    ImageView postImage;
    Button submit_button;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);

        messagetext = findViewById(R.id.message);
        chooseImageText = findViewById(R.id.choose_text);
        postImage = findViewById(R.id.post_image);
        submit_button = findViewById(R.id.submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    //Code to Upload Image
                    uploadRequest(messagetext.getText().toString());
                }
            }
        });

        chooseImageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 101);
            }
        });

    }

    private void uploadRequest(String message){

        //Code to upload the Message

    }

    private void uploadImage(){

        //Code to Upload the Image

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode==RESULT_OK){
            if (data != null) {
                imageUri = data.getData();
                Glide.with(getApplicationContext()).load(imageUri).into(postImage);
            }
        }
    }

    private boolean isValid() {
        if (messagetext.getText().toString().isEmpty()) {
            messagetext.setError("Field's can't be Empty");
            return false;
        }
        return true;
    }
}