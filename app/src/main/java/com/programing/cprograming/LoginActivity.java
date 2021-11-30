package com.programing.cprograming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.programing.cprograming.Fragment.AccountFragment;

import es.dmoral.toasty.Toasty;
import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends AppCompatActivity {

    private Button loginbtn;
    private EditText temail,tpassword;
    private TextView gotoreg;
    ImageView imageView,google;
    SignInButton signInButton;
    TextView textView;
    private static final int RC_SIGN_IN = 1;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private GifImageView loading;
    FirebaseDatabase database ;
    DatabaseReference myRef;
    int e;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        loginbtn = (Button) findViewById(R.id.loginbtn);
        temail = (EditText) findViewById(R.id.loginemail);
        tpassword = (EditText) findViewById(R.id.loginpassword);
        imageView = (ImageView) findViewById(R.id.loginclose);
        gotoreg = (TextView) findViewById(R.id.gotoregistration);
        mAuth = FirebaseAuth.getInstance();
        loading = (GifImageView) findViewById(R.id.logload);
        database = FirebaseDatabase.getInstance();

        Intent intent=getIntent();
        String mmm=intent.getStringExtra("registrationcode");
        if (!(TextUtils.isEmpty(mmm))){
            //Snackbar.make(getApplicationContext(),"",Snackbar.LENGTH_SHORT).show();
            Toasty.info(this, "Please!Check your email and Verify your Gmail id", Toast.LENGTH_LONG, true).show();

        }

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        e = 21;
                        break;
                    // ...
                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainHomeActivity.class));
                finish();
                Animatoo.animateSlideUp(LoginActivity.this);
            }
        });
        gotoreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
                Animatoo.animateSlideUp(LoginActivity.this);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = temail.getText().toString();
                String password = tpassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    temail.setError("required *");
                    temail.requestFocus();
                } else if (TextUtils.isEmpty(password)) {

                    tpassword.setError("required *");
                    tpassword.requestFocus();
                } else {
                    loading.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        final String h = mAuth.getCurrentUser().getUid();
                                        final String mail = mAuth.getCurrentUser().getEmail();
                                        final String[] name = {""};
                                        final String[] image = new String[1];
                                        if (mAuth.getCurrentUser().isEmailVerified()) {
                                            myRef = database.getReference().child("Users").child(h);
                                            myRef.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    name[0] = dataSnapshot.child("name").getValue(String.class).toString();
                                                    image[0] = dataSnapshot.child("imagUrl").getValue(String.class).toString();
                                                    // Toast.makeText(LoginActivity.this,name[0].toString(),Toast.LENGTH_SHORT).show();
                                                    SharedPreferences sp = getApplicationContext().getSharedPreferences
                                                            ("com.programing.cprograming", Context.MODE_PRIVATE);
                                                    sp.edit().putString("user_id", h).apply();
                                                    sp.edit().putString("user_name", name[0]).apply();
                                                    sp.edit().putString("user_email", mail).apply();
                                                    sp.edit().putString("imageUrl", image[0]).apply();
                                                    Snackbar.make(view, name[0], Snackbar.LENGTH_SHORT).show();
                                                    startActivity(new Intent(LoginActivity.this, MainHomeActivity.class));
                                                    finish();
                                                    loading.setVisibility(View.INVISIBLE);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });


                                            //

                                        } else {

                                            Snackbar.make(view, "Please!Verify your email\nTry Again!", Snackbar.LENGTH_SHORT).show();
                                            loading.setVisibility(View.INVISIBLE);
                                        }

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Snackbar.make(view, "Check your email & password.\nTry Again!", Snackbar.LENGTH_SHORT).show();
                                        loading.setVisibility(View.INVISIBLE);
                                    }

                                    // ...
                                }
                            });


                }
            }
        });



    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
                String m = String.valueOf(personPhoto);
                Toast.makeText(LoginActivity.this,"Successful",Toast.LENGTH_SHORT).show();

                SharedPreferences sp = getApplicationContext().getSharedPreferences
                        ("com.programing.cprograming", Context.MODE_PRIVATE);
                sp.edit().putString("user_id", m);
                sp.edit().putString("user_name", personName).apply();
                sp.edit().putString("user_email", personEmail).apply();
                sp.edit().putString("imageUrl", personPhoto.toString()).apply();
                Intent intent=new Intent(LoginActivity.this,MainHomeActivity.class);
                startActivity(intent);
                finish();
                Animatoo.animateSwipeLeft(LoginActivity.this);

            }

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());

        }

    }


}