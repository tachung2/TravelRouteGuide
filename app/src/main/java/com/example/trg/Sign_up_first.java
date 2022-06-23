package com.example.trg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Sign_up_first extends AppCompatActivity {

    String user_id = getIntent().getStringExtra("user_id");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page_1);
    }
}
