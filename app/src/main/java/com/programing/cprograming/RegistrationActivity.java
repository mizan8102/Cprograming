package com.programing.cprograming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;

import es.dmoral.toasty.Toasty;
import pl.droidsonroids.gif.GifImageView;

public class RegistrationActivity extends AppCompatActivity {


    private EditText tname,tinstitute,temail,tpassword;
    private TextView gotologin;
    private Button regis;
    private ImageView close;
    private FirebaseAuth mAuth;
    FirebaseDatabase database ;
    private GifImageView mkLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
      checStatusBar();
      tname=findViewById(R.id.registrationname);
     // tinstitute=findViewById(R.id.registrationinstitute);
      temail=findViewById(R.id.registrationemail);
      tpassword=findViewById(R.id.registrationpassword);
      regis=(Button)findViewById(R.id.registrationbutton);
      mkLoader=(GifImageView) findViewById(R.id.progresslogin);
      gotologin=(TextView)findViewById(R.id.gotologin);
      close=(ImageView)findViewById(R.id.registrationclose);
        mAuth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
      gotologin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
              finish();
              Animatoo.animateSlideDown(RegistrationActivity.this);
          }
      });
      close.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             finish();
          }
      });
      regis.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(final View view) {
              final String name=tname.getText().toString();
            //  final String institute=tinstitute.getText().toString();
              String email=temail.getText().toString();
              String password=tpassword.getText().toString();
              regis.setEnabled(false);
              if (TextUtils.isEmpty(name)){
                  tname.setError("required *");
                  tname.requestFocus();
              }
              /*else if (TextUtils.isEmpty(institute)){
                  tinstitute.setError("required *");
                  tinstitute.requestFocus();
              }*/
              else if (TextUtils.isEmpty(email)){
                  temail.setError("required *");
                  temail.requestFocus();
              }
              else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                  temail.setError("Your Email is not correct");
                  temail.requestFocus();
              }
              else if (TextUtils.isEmpty(password)){
                  tpassword.setError("required *");
                  tpassword.requestFocus();
              } else if (password.length()<6){
                  tpassword.setError("Password must be upto 6 digits");
                  tpassword.requestFocus();
              }
              else {
                    mkLoader.setVisibility(View.VISIBLE);
                  mAuth.createUserWithEmailAndPassword(email, password)
                          .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                  if (task.isSuccessful()) {
                                      // Sign in success, update UI with the signed-in user's information

                                      mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                          @Override
                                          public void onComplete(@NonNull Task<Void> task) {

                                              if (task.isSuccessful()){
                                                  String firebaseUser=mAuth.getCurrentUser().getUid();

                                                  DatabaseReference reference=database.getReference().child("Users").child(firebaseUser);
                                                  HashMap<String,Object> hashMap=new HashMap<>();
                                                  hashMap.put("id",firebaseUser);
                                                  hashMap.put("name",name);
                                                  hashMap.put("institute","");
                                                  hashMap.put("imagUrl","");
                                                  reference.setValue(hashMap);

                                                  //Toast.makeText(RegistrationActivity.this,"sucessfull",Toast.LENGTH_SHORT).show();
                                                  Toasty.info(getApplicationContext(), "Please!Check your email and Verify your Gmail id", Toast.LENGTH_LONG, true).show();

                                                  Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                                                  intent.putExtra("registrationcode","120");
                                                  startActivity(intent);
                                                  finish();
                                                  Animatoo.animateSlideDown(RegistrationActivity.this);
                                                  mkLoader.setVisibility(View.INVISIBLE);
                                                 regis.setEnabled(true);


                                              }
                                              else {

                                                  Toast.makeText(RegistrationActivity.this,"Registration can't success",Toast.LENGTH_SHORT).show();
                                                  Snackbar.make(view,"Registration isn't successful.\n Try Again!",Snackbar.LENGTH_SHORT).show();

                                                  mkLoader.setVisibility(View.INVISIBLE);
                                                  regis.setEnabled(true);

                                              }
                                          }
                                      });


                                  } else {
                                      // If sign in fails, display a message to the user.

                                      Toast.makeText(RegistrationActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
                                      mkLoader.setVisibility(View.INVISIBLE);
                                      regis.setEnabled(true);
                                  }

                                  // ...
                              }
                          });

              }



          }
      });




    }



    private void checStatusBar(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            Window window=getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getSupportActionBar().hide();

        }

    }
}