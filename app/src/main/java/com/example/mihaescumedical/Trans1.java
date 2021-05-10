package com.example.mihaescumedical;

import androidx.appcompat.app.AppCompatActivity;



import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Trans1 extends AppCompatActivity {
    private static int SPLASH_SCREEN=2500;
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo,slogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_trans1);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.botton_animation);

        image=findViewById(R.id.image);
        logo=findViewById(R.id.logo_txt);
        slogo=findViewById(R.id.slogo_txt) ;

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogo.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(Trans1.this,Login.class);
                Pair[] pairs = new Pair[2];
                pairs[0]=new Pair<View,String>(image,"logo_image");
                pairs[1]=new Pair<View,String>(logo,"logo_txt");

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Trans1.this,pairs);
                    startActivity(intent,options.toBundle());


                }
                //
                //startActivity(intent);
                //finish();
            }
        },SPLASH_SCREEN);
    }

}