package com.programing.cprograming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import it.beppi.tristatetogglebutton_library.TriStateToggleButton;

public class IntroduceDetailsActivity extends AppCompatActivity {


    private TextToSpeech mTTS;
    private TextView textView,title;
    private SeekBar mSeekBarSpeed;
    private ImageView mButtonSpeak,mButtonpause;
    private String text;
    private TriStateToggleButton tstb_1;

    private ImageView share,close;
    private String eng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_details);
        checStatusBar();
        mButtonSpeak = findViewById(R.id.button_speak);
        textView=(TextView)findViewById(R.id.infotxtdel);
        title=(TextView)findViewById(R.id.deftitle);
        mButtonpause=(ImageView)findViewById(R.id.button_pause);
        close=(ImageView)findViewById(R.id.txtidfinish);
        share=(ImageView)findViewById(R.id.txtidshare);
        tstb_1 = (TriStateToggleButton) findViewById(R.id.tstb_1);
        tstb_1.setOnToggleChanged(new TriStateToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(TriStateToggleButton.ToggleStatus toggleStatus, boolean booleanToggleStatus, int toggleIntValue) {
                switch (toggleStatus) {
                    case off:
                        textView.setText(eng);
                        break;
                    //case mid: break;
                    case on:
                        Toast.makeText(IntroduceDetailsActivity.this,"on",Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textView.getText().toString());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "Share Via");
                startActivity(shareIntent);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent=getIntent();
        String m=intent.getStringExtra("c_info");
        text=intent.getStringExtra("c_def");
        title.setText(m);
        textView.setText(text);
        eng=text;
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        mSeekBarSpeed = findViewById(R.id.seek_bar_speed);
        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
                mButtonSpeak.setVisibility(View.INVISIBLE);
                mButtonpause.setVisibility(View.VISIBLE);

            }
        });
        mButtonpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTTS.stop();
                mButtonpause.setVisibility(View.INVISIBLE);
                mButtonSpeak.setVisibility(View.VISIBLE);

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
    private void speak() {



        float speed = (float) mSeekBarSpeed.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;
        mTTS.setSpeechRate(speed);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }
    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}
