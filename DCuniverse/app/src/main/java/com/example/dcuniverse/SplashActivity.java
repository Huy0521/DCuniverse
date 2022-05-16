package com.example.dcuniverse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    ImageView batlogo,suplogo,flashlogo,wonderlogo,dclogo;
    TextView txtAppname,txtDesign;
    Animation rightAnimation,leftAnimation,topAnimation,bottomAnimation,centerAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        addView();
    }

    private void addView() {
        batlogo=findViewById(R.id.BatLogo);
        suplogo = findViewById(R.id.Suplogo);
        wonderlogo = findViewById(R.id.WonderLogo);
        flashlogo = findViewById(R.id.FlashLogo);
        dclogo = findViewById(R.id.DClogo);
        txtAppname = findViewById(R.id.txtAppName);
        txtDesign = findViewById(R.id.txtDesign);
        rightAnimation = AnimationUtils.loadAnimation(this,R.anim.right_animation);
        leftAnimation = AnimationUtils.loadAnimation(this,R.anim.left_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        centerAnimation = AnimationUtils.loadAnimation(this,R.anim.center_animation);
        batlogo.setAnimation(rightAnimation);
        suplogo.setAnimation(leftAnimation);
        flashlogo.setAnimation(topAnimation);
        wonderlogo.setAnimation(bottomAnimation);
        dclogo.setAnimation(centerAnimation);
        txtAppname.setAnimation(leftAnimation);
        txtDesign.setAnimation(rightAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        },6000
        );

    }
}