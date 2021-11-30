package com.programing.cprograming;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.programing.cprograming.Fragment.AccountFragment;
import com.programing.cprograming.Fragment.DashboardFragment;
import com.programing.cprograming.Fragment.HomeFragment;
import com.programing.cprograming.SharingCenter.SocialHome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    GoogleSignInClient mGoogleSignInClient;

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    private CircleImageView profileimage;
    private TextView profilename,profileemail;
    private Button hidebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Coding Hero");
        setSupportActionBar(toolbar);
        final ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_sort_24);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view=navigationView.getHeaderView(0);
        profileimage=view.findViewById(R.id.profile_image);
        profileemail=view.findViewById(R.id.profileemailid);
        profilename=view.findViewById(R.id.textView);
        hidebutton=view.findViewById(R.id.hideprofilelog);
// start


//Tab end
        SharedPreferences sp=getApplicationContext().getSharedPreferences
                ("com.programing.cprograming", Context.MODE_PRIVATE);
        String name=sp.getString("user_name","");
        String email=sp.getString("user_email","");
        String picUrl=sp.getString("imageUrl","");
        profileemail.setText(email);
        profilename.setText(name);

        if (!(TextUtils.isEmpty(name))){
            if (TextUtils.isEmpty(picUrl)){
                Glide.with(view).load(R.drawable.info).placeholder(R.drawable.info).into(profileimage);

            }else {

                Glide.with(view).load(picUrl).placeholder(R.drawable.info).into(profileimage);
            }


        }

    else {
        profileimage.setVisibility(View.INVISIBLE);
        hidebutton.setVisibility(View.VISIBLE);


        }
    hidebutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainHomeActivity.this,LoginActivity.class));
            finish();
            Animatoo.animateSwipeLeft(MainHomeActivity.this);
        }
    });





        frameLayout=(FrameLayout)view.findViewById(R.id.frameLayout);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottonnavigationViewhome);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigaton);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
    }
        // Bottom Navigation select Fragment

    int a;
    private BottomNavigationView.OnNavigationItemSelectedListener navigaton=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectFragment=null;
                    switch (item.getItemId()){
                        case R.id.menu_home:
                            a=2;

                            //selectFragment= new HomeFragment();
                            break;
                        case R.id.menu_dashboard:
                            selectFragment=new DashboardFragment();
                            break;
                      /*  case R.id.menu_notifications:
                            selectFragment=new NotificationsFragment();
                            break;*/
                        case R.id.menu_account:
                            selectFragment=new AccountFragment();
                            break;

                    }
                    if (a==2){

                        Intent intent = new Intent(getApplicationContext(), MainHomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectFragment).commit();
                    }


                    return true;
                }
            };







    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                break;

            case R.id.action_feedback:
               // RateDialogManager.showRateDialog(MainHomeActivity.this,savedInstanceState);
                startActivity(new Intent(MainHomeActivity.this,FeedbackForm.class));
                break;
            case R.id.action_about:
                // RateDialogManager.showRateDialog(MainHomeActivity.this,savedInstanceState);
                startActivity(new Intent(MainHomeActivity.this,AboutActivity.class));
                Animatoo.animateSlideDown(this);
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {

        return super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_book:
                SharedPreferences sp=getSharedPreferences
                        ("com.programing.cprograming", Context.MODE_PRIVATE);
                String email=sp.getString("user_id", "");
                if (TextUtils.isEmpty(email)){
                    startActivity(new Intent(MainHomeActivity.this, LoginActivity.class));
                   finish();
                    Animatoo.animateSlideLeft(this);
                }
                else {
                    startActivity(new Intent(MainHomeActivity.this, SocialHome.class));
                    Animatoo.animateSlideDown(this);
                }
                break;
            case R.id.nav_about:
                startActivity(new Intent(MainHomeActivity.this,AboutActivity.class));
                Animatoo.animateSlideDown(this);
                break;
           // case R.id.nav_rate:
                //Toast.makeText(getApplication(), "Slideshow", Toast.LENGTH_SHORT).show();

               // break;
            case R.id.nav_help:
                Toast.makeText(getApplication(), "Slideshow", Toast.LENGTH_SHORT).show();
                break;
           /* case R.id.nav_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "C Programme");
                startActivity(Intent.createChooser(sharingIntent, "Share"));
                break;*/



            case R.id.nav_logout:
                signOut();
                SharedPreferences ssp=getApplicationContext().getSharedPreferences
                        ("com.programing.cprograming", Context.MODE_PRIVATE);
                ssp.edit().remove("user_id").apply();
                ssp.edit().remove("user_name").apply();
                ssp.edit().remove("user_email").apply();
                ssp.edit().remove("imageUrl").apply();
                Toast.makeText(getApplicationContext(),"Log Out",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainHomeActivity.this,LoginActivity.class));
                finish();
                Animatoo.animateSwipeLeft(MainHomeActivity.this);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to exit the application?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        final public void onClick(DialogInterface arg0, int arg1) {
                            MainHomeActivity.super.onBackPressed();
                        }
                    }).create().show();
        }





    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(MainHomeActivity.this,"log out succes",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}