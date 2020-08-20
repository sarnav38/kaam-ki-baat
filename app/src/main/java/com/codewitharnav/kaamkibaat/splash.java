package com.codewitharnav.kaamkibaat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static java.lang.Thread.sleep;

public class splash extends AppCompatActivity {
//    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        im = findViewById(R.id.imageView_an);
//        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.myanim);
//        im.startAnimation(myanim);
        View im = findViewById(R.id.imageView_an);
        View t = findViewById(R.id.textView);
        Spring(im,400f);
        Spring(t,-440f);



        Thread mythread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent i = new Intent(splash.this,MainActivity.class);
                    startActivity(i);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        mythread.start();
    }

    public void Spring(View view, float p){
        SpringAnimation springAnimation = new SpringAnimation(view, DynamicAnimation.TRANSLATION_Y);
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springForce.setFinalPosition(p);
        springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springAnimation.setSpring(springForce);
        springAnimation.start();
    }
//    public void SpringR(View view, float p){
//        SpringAnimation springAnimation = new SpringAnimation(view, DynamicAnimation.ROTATION_X);
//        SpringForce springForce = new SpringForce();
//        springForce.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
//        springForce.setFinalPosition(p);
//        springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
//        springAnimation.setSpring(springForce);
//        springAnimation.start();
//    }
}