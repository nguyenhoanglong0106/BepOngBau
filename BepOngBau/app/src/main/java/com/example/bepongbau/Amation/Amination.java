package com.example.bepongbau.Amation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bepongbau.Login.ActivityLogin;
import com.example.bepongbau.R;

public class Amination extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amination);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Amination.this, ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}