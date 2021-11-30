package com.programing.cprograming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class PdfShowActivity extends AppCompatActivity {

    private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_show);
        getSupportActionBar().setTitle("PDF View");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pdfView=(PDFView)findViewById(R.id.pdfView);
        Intent intent=getIntent();
        String m=intent.getStringExtra("bookid");
        if (TextUtils.isEmpty(m)){
            Toast.makeText(this,"Books cannot find",Toast.LENGTH_SHORT).show();

        }
        else {
            pdfView.fromAsset(m).load();
        }

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