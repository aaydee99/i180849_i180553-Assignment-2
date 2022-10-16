package com.ass2.i180849_i180553;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    Handler mHandler;
    Runnable mRunnable;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }
            }
        };
        // its trigger runnable after 4000 millisecond.
        mHandler.postDelayed(mRunnable,1000);

    }
}