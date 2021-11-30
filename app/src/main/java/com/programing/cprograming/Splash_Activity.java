package com.programing.cprograming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.programing.cprograming.Adapter.SliderAdapterExample;
import com.programing.cprograming.Model.SliderModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Splash_Activity extends AppCompatActivity {


    CardView started,details;
    int images[]={
            R.drawable.cod,
         R.drawable.logoc,
            R.drawable.homeright,
            R.drawable.cprograming
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_);
        checStatusBar();
        started=(CardView) findViewById(R.id.startedwork);
        details=(CardView)findViewById(R.id.detailsutc);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://utcworld.net"));
                startActivity(browserIntent);
            }
        });
        started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp=getApplicationContext().getSharedPreferences
                        ("com.programing.cprograming", Context.MODE_PRIVATE);
                String email=sp.getString("user_name","");
                if (TextUtils.isEmpty(email)){
                    startActivity(new Intent(Splash_Activity.this,LoginActivity.class));
                    finish();
                    Animatoo.animateSlideLeft(Splash_Activity.this);
                }
                else {
                    Intent intent=new Intent(Splash_Activity.this,MainHomeActivity.class);
                    startActivity(intent);
                    finish();
                    Animatoo.animateSwipeLeft(Splash_Activity.this);
                }




            }
        });

        SliderView sliderView = findViewById(R.id.imageSlider);

        SliderModel sliderModel=new SliderModel();
        SliderAdapterExample adapter = new SliderAdapterExample(this,images);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(2); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    private void checStatusBar(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            Window window=getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getSupportActionBar().hide();

        }

    }
}