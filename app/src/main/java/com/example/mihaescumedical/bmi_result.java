package com.example.mihaescumedical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class bmi_result extends AppCompatActivity {
Button mrecalculatebmi;
TextView mbmidisplay,mbmicategory,mgender;
Intent intent;
ImageView mimageview;
CardView card;
String mbmi;
float intbmi ;
String height,weight;
float intheight,intweight;
RelativeLayout mbackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bmi_result);



        intent=getIntent();
        card=findViewById(R.id.card_r);
        mimageview=findViewById(R.id.imageview);
        mbmidisplay=findViewById(R.id.bmidisplay);
        mbmicategory=findViewById(R.id.bmicategory);
        mgender=findViewById(R.id.genderdisplay);
        mrecalculatebmi=findViewById(R.id.recalculatebmi);


        height=intent.getStringExtra("height");
        weight=intent.getStringExtra("weight");

        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);

        intheight=intheight/100;
        intbmi=intweight/(intheight*intheight);
        mbmi=Float.toString(intbmi);

        if(intbmi<16)
        {mbmicategory.setText("Severe Thinness");
        card.getBackground().setTint(Color.RED);
        mimageview.setImageResource(R.drawable.crosss);
        }

        else   if(intbmi<17 && intbmi>=16)
        {   mbmicategory.setText("Moderate Thinness");
            card.getBackground().setTint(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        }

        else   if(intbmi<18.4 && intbmi>=17)
        {   mbmicategory.setText("Mild Thinness");
            card.getBackground().setTint(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        }
        else   if(intbmi<25 && intbmi>=18.4)
        {   mbmicategory.setText("Normal");
            card.getBackground().setTint(Color.GREEN);
            mimageview.setImageResource(R.drawable.ok);
        }
        else   if(intbmi<29.4 && intbmi>=25)
        {   mbmicategory.setText("Overweight");
            card.getBackground().setTint(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        }
        else
        {   mbmicategory.setText("Obese Class I");
            card.getBackground().setTint(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        }

        mgender.setText(intent.getStringExtra("gender"));
        mbmidisplay.setText(mbmi);

        mrecalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(bmi_result.this,bmi.class);
                startActivity(intent);
                finish();
            }
        });


    }
}