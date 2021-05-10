package com.example.mihaescumedical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    TextInputLayout pass1,pass2,pass3;
    ImageView image_change_c;
    String f_email, f_pass;
    Button changebtn;
    private boolean shouldFinish = false;
    private static final String TAG = "ManageUserActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password);



        pass2=findViewById(R.id.pass);
        pass3=findViewById(R.id.pass_ver);
        image_change_c=findViewById(R.id.change_img);
        changebtn=findViewById(R.id.change_botton);




        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validatePassword2() || !validatePassword())
                    return;
                Toast.makeText(ChangePassword.this, "Password successfully changed”", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(ChangePassword.this,MainActivity.class);
                Pair[] pairs_c = new Pair[1];
                pairs_c[0]=new Pair<View,String>(image_change_c,"trans_change");
                shouldFinish = true;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ChangePassword.this,pairs_c);

                    startActivity(intent,options.toBundle());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(ChangePassword.this,MainActivity.class);
        Pair[] pairs_c = new Pair[1];
        pairs_c[0]=new Pair<View,String>(image_change_c,"trans_change");
        shouldFinish = true;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ChangePassword.this,pairs_c);

            startActivity(intent,options.toBundle());
            }
    }
    private Boolean validatePassword(){
        String val=pass2.getEditText().getText().toString();
        String passwordVal= "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&*+=!])(?=\\S+$).{6,}$";

        if (!val.matches(passwordVal))
        { pass2.setError("Password must contain one upper case letter,one lower case letter, one number, one special caracter  and at least 6 characters");
            return false;

        }
        else {
            pass2.setError(null);
            return true;
        }
    }
    private Boolean validatePassword2(){
        String val=pass2.getEditText().getText().toString();
        String val2=pass3.getEditText().getText().toString();

       if(!val.matches(val2)){
            pass2.setError("Password doesn't match");
           pass3.setError("Password doesn't match");
            return false;
        }

        else{
            pass2.setError(null);
            return true;
        }
    }

}