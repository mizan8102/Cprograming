package com.programing.cprograming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;

import ng.max.slideview.SlideView;

public class IntroductionActivity extends AppCompatActivity {

    SlideView slideView,slideViewhis,slideViewfea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        getSupportActionBar().setTitle("Introduction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        slideView = (SlideView) findViewById(R.id.slideViewwhat);
        slideViewhis=(SlideView)findViewById(R.id.slideViewhis);
        slideViewfea=(SlideView)findViewById(R.id.slideViewfeature);
        slideView.setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideView slideView) {
                // vibrate the device
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);

                // go to a new activity
                Intent intent=new Intent(IntroductionActivity.this, IntroduceDetailsActivity.class);
                String n=getString(R.string.c_info);
                String def=getString(R.string.c_def);
                intent.putExtra("c_info",n);
                intent.putExtra("c_def",def);
                startActivity(intent);
            }
        });
        slideViewhis.setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideView slideView) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);

                // go to a new activity
                Intent intent=new Intent(IntroductionActivity.this, IntroduceDetailsActivity.class);
                String g=getString(R.string.c_his);
                intent.putExtra("c_info",g);
                String ff=getString(R.string.c_def_history);
                intent.putExtra("c_def",ff);
                startActivity(intent);
            }
        });
        slideViewfea.setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideView slideView) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);

                // go to a new activity
                Intent intent=new Intent(IntroductionActivity.this, IntroduceDetailsActivity.class);
                String g=getString(R.string.c_fea);
                intent.putExtra("c_info",g);
                String ff=getString(R.string.c_def_feature);
                intent.putExtra("c_def",ff);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}