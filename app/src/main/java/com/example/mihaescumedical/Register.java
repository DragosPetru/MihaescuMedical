package com.example.mihaescumedical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    TextInputLayout regName,regEmail,regPassword,regPassword2;
    Button regBtn;
    Boolean aux1,aux2,aux3,aux4;
    FirebaseAuth fAuth;
    public static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        regName=findViewById(R.id.fullname);
        regEmail=findViewById(R.id.email);
        regPassword=findViewById(R.id.password1);
        regPassword2=findViewById(R.id.password2);
        regBtn=findViewById(R.id.signin);

        fAuth = FirebaseAuth.getInstance();




        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aux1=validateEmail();
                aux2=validateNume();
                aux3=validatePassword();
                aux4=validatePassword2();

                if(!aux1|| !aux2 || !aux3 || !aux4)
                    return;

                fAuth.createUserWithEmailAndPassword(regEmail.getEditText().getText().toString(),regPassword.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            // send verification linsk

                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                }
                            });


                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getApplicationContext(), Login.class);
                             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                               startActivity(intent);
                              finish();
                        }

                        else
                        {
                            Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

             //   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
             //   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             //   startActivity(intent);
              //  finish();

            }
        });


    }

    private Boolean validateNume(){
       String val=regName.getEditText().getText().toString();

        if(val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;}
        else {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val=regEmail.getEditText().getText().toString();
        String emailPattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";;
        if(val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            regEmail.setError("Invalid email address");
            return false;
                }

            {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val=regPassword.getEditText().getText().toString();
        String passwordVal= "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&*+=!])(?=\\S+$).{6,}$";


        if(val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        }
            if (!val.matches(passwordVal))
        { regPassword.setError("Password must contain one upper case letter,one lower case letter, one number, one special caracter  and at least 6 characters");
            return false;

        }
            else {
            regPassword.setError(null);
            return true;
        }
    }

    private Boolean validatePassword2(){
        String val=regPassword2.getEditText().getText().toString();
        String val2=regPassword.getEditText().getText().toString();

        if(val.isEmpty()) {
            regPassword2.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(val2)){
            regPassword2.setError("Password doesn't match");
            return false;
        }

            else{
            regPassword2.setError(null);
            return true;
        }
    }



    @Override
    public void onBackPressed() {
        Intent intent =new Intent(Register.this,Login.class);
        startActivity(intent);
        finish();
    }

}