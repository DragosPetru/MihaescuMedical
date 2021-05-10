package com.example.mihaescumedical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    TextView logoText,slogoText;
    TextInputLayout email;
    Button send_btn;
    ImageView image;
    private boolean shouldFinish = false;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forget_password);

        image=findViewById(R.id.forget_img);
        logoText=findViewById(R.id.txt);
        slogoText=findViewById(R.id.txt2);
        send_btn=findViewById(R.id.forget_botton);
        email=findViewById(R.id.emai_txt);

        fAuth = FirebaseAuth.getInstance();

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateEmail())
                    return;
                resetpassword();
            }
        });


    }

    private void resetpassword(){
        fAuth.sendPasswordResetEmail(email.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgetPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                    email.getEditText().getText().clear();
                } else {
                    Toast.makeText(ForgetPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                    email.getEditText().getText().clear();
                }
            }
        });

    }
    private Boolean validateEmail(){
        String val=email.getEditText().getText().toString();
        String emailPattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";;
        if(val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            email.setError("Invalid email address");
            return false;
        }

        {
            email.setError(null);
            return true;
        }
    }
    public void onBackPressed() {
        Intent intent =new Intent(ForgetPassword.this,Login.class);
        Pair[] pairs = new Pair[5];
        pairs[0]=new Pair<View,String>(image,"logo_image");
        pairs[1]=new Pair<View,String>(logoText,"logo_txt1");
        pairs[2]=new Pair<View,String>(slogoText,"slogo_txt");
        pairs[3]=new Pair<View,String>(email,"user_tran");
        pairs[4]=new Pair<View,String>(send_btn,"login_signup_tran");

        shouldFinish = true;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ForgetPassword.this,pairs);

            startActivity(intent,options.toBundle());


        }
    }

}
