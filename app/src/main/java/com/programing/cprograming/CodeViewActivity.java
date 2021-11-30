package com.programing.cprograming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import io.github.kbiakov.codeview.CodeView;

public class CodeViewActivity extends AppCompatActivity {

    TextView codetitleh,outputtext,descriptiontext,desLabel,outputlabel;
    CodeView codetext;
    ImageView despic,outputPic;
    private String code;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checStatusBar();
        setContentView(R.layout.activity_code_view);
        codetitleh=(TextView)findViewById(R.id.codehTitle);
       codetext=(CodeView)findViewById(R.id.txtcode);
        outputtext=(TextView)findViewById(R.id.idoutput);
        descriptiontext=(TextView)findViewById(R.id.iddescription);
        despic=(ImageView)findViewById(R.id.descriptionPic);
        outputPic=(ImageView)findViewById(R.id.OutputPic);
        desLabel=(TextView)findViewById(R.id.descriptiondetailslabel);
        outputlabel=(TextView)findViewById(R.id.outputlabel);
        Intent intent=getIntent();
        String codetitle=intent.getStringExtra("codeTitle");
         code=intent.getStringExtra("code");
        String output=intent.getStringExtra("output");
        String codePic=intent.getStringExtra("codePic");
        String des=intent.getStringExtra("description");
        String ouputPic=intent.getStringExtra("outputPic");
        final String youtuve=intent.getStringExtra("youtuve");
        codetitleh.setText(codetitle);
        codetext.setCode(code);
        outputtext.setText(output);
        descriptiontext.setText(des);
        if (!(TextUtils.isEmpty(codePic))) {

            Glide.with(CodeViewActivity.this).load(codePic).placeholder(R.drawable.logoc).into(despic);
        }
        if (!(TextUtils.isEmpty(ouputPic))) {

            Glide.with(CodeViewActivity.this).load(ouputPic).placeholder(R.drawable.logoc).into(outputPic);
        }

        if ((TextUtils.isEmpty(des))) {

            desLabel.setVisibility(View.INVISIBLE);
        }
        if ((TextUtils.isEmpty(output))) {

            outputlabel.setVisibility(View.INVISIBLE);
        }
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        if (!(TextUtils.isEmpty(youtuve))){
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = youtuve;
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });

        }else {

            youTubePlayerView.setVisibility(View.INVISIBLE);
        }


    }
    private void checStatusBar(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            Window window=getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getSupportActionBar().hide();

        }

    }

    public void share(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "codetext.getCode.toString()");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "Share Via");
        startActivity(shareIntent);
    }

    public void closeid(View view) {
        onBackPressed();
    }
}