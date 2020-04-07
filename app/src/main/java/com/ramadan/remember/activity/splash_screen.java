package com.ramadan.remember.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.ramadan.remember.R;

@SuppressLint("Registered")
public class splash_screen extends AppCompatActivity {
    private static final int splash_screen_timeout = 2500;
    LinearLayout l1, l2;
    Animation uptodown, downtoup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this, main.class);
                Animatoo.animateZoom(splash_screen.this);
                startActivity(intent);
                finish();
            }
        }, splash_screen_timeout);

        l1 = findViewById(R.id.left);
        l2 = findViewById(R.id.right);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

    }

}

