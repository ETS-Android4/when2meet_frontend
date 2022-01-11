package com.example.when2meet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceStare) {
        super.onCreate(savedInstanceStare);
        setContentView(R.layout.activity_splash);

        ImageView imageView;
        ImageView load1, load2, load3;
        imageView = findViewById(R.id.imageView);
        load1 = findViewById(R.id.load1);
        load2 = findViewById(R.id.load2);
        load3 = findViewById(R.id.load3);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.frontpng));
        load1.setImageDrawable(getResources().getDrawable(R.drawable.loading1));

        final Animation animTransRight = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        final Animation animTransRight1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        final Animation animTransRight2 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        final Animation animTransRight3 = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        imageView.startAnimation(animTransRight);
        load1.startAnimation(animTransRight1);
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                load2.setImageDrawable(getResources().getDrawable(R.drawable.loading2));
                load2.startAnimation(animTransRight2);
            }
        },750);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                load3.setImageDrawable(getResources().getDrawable(R.drawable.loading3));
                load3.startAnimation(animTransRight3);
            }
        },1500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}