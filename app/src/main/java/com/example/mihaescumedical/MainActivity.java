package com.example.mihaescumedical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth fAuth;

    GoogleSignInClient gGoogleSignInClient;
    ImageView image_change,image_ibm;
    private boolean shouldFinish = false;
    CardView c_logout,c_ibm,c_virus,c_map,c_change,c_medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);




        fAuth = FirebaseAuth.getInstance();


        c_logout=findViewById(R.id.card6);
        c_change =findViewById(R.id.card5);
        c_virus =findViewById(R.id.card2);
        c_map =findViewById(R.id.card1);
        c_medicine =findViewById(R.id.card3);
        c_ibm =findViewById(R.id.card4);

        image_change=findViewById(R.id.image_change);
        image_ibm=findViewById(R.id.image_bmi);

        c_ibm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,bmi.class);
                Pair[] pairs = new Pair[1];
                pairs[0]=new Pair<View,String>(image_ibm,"bmi_change");
                shouldFinish = true;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);

                    startActivity(intent,options.toBundle());
                    }
            }
        });
        c_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignIn.getClient(getApplicationContext(), GoogleSignInOptions.DEFAULT_SIGN_IN).signOut();
                fAuth.signOut();
                Intent intent= new Intent(MainActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                finish();
            }
        });

        c_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,ChangePassword.class);
                Pair[] pairs = new Pair[1];
                pairs[0]=new Pair<View,String>(image_change,"trans_change");
                shouldFinish = true;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);

                    startActivity(intent,options.toBundle());
                   }
            }
        });
  /*      logut_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                GoogleSignIn.getClient(getApplicationContext(), GoogleSignInOptions.DEFAULT_SIGN_IN).signOut();
                fAuth.signOut();
                Intent intent= new Intent(MainActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
          //      Pair[] pairs = new Pair[1];
           //     pairs[0]=new Pair<View,String>(image,"logo_image");
                startActivity(intent);
                finish();
          //      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
          //          ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
         //           startActivity(intent,options.toBundle());}
            }

        });
*/

    }
    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        gGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}