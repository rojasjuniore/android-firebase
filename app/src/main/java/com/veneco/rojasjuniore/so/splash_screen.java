package com.veneco.rojasjuniore.so;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class splash_screen extends AppCompatActivity {

    private static final long SPLASH_SCREEN = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(splash_screen.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        };


        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN);
    }
}
