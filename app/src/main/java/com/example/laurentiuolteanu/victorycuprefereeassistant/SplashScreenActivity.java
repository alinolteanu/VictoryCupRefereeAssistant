package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    private Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mWaitHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                try {
                    Intent intent = new Intent(getApplicationContext(), SelectMatchActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 1000);
    }
}
