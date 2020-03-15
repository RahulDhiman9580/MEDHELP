package com.example.medhelp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ImageView img = findViewById(R.id.img);
        TextView text= findViewById(R.id.text);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        img.startAnimation(animation);
        text.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent (Splash.this, Login.class);
                startActivity(i);
                finish();
            }
        }, 3000);//DELLAY TIME OF SPLASH SCREEN

    }
    }

