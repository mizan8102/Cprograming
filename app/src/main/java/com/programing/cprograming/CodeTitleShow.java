package com.programing.cprograming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.programing.cprograming.Adapter.CodeAdapter;
import com.programing.cprograming.Adapter.RecycleAdapter;
import com.programing.cprograming.Model.CodeModel;
import com.programing.cprograming.Model.DataModel;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class CodeTitleShow extends AppCompatActivity {

    private TextView codetitleshow;
    private RecyclerView recyclerView;
    private ImageView codeback;
    private DatabaseReference reference;
    private GifImageView progressbar;
    private List<CodeModel> list;
    private CodeAdapter codeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checStatusBar();
        setContentView(R.layout.activity_code_title_show);
        codetitleshow=(TextView)findViewById(R.id.codetitleShow);
        recyclerView=(RecyclerView)findViewById(R.id.coderecycler);
        codeback=(ImageView)findViewById(R.id.codeshowback);
        progressbar=(GifImageView)findViewById(R.id.codingload);
        reference= FirebaseDatabase.getInstance().getReference();
        list=new ArrayList<>();
        codeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        String value = getIntent().getExtras().getString("CodeT");
        codetitleshow.setText(value);
        // Basic Programme
        if (value.contains("Basic Programs")){

            progressbar.setVisibility(View.VISIBLE);
            DatabaseReference bref=reference.child("Programme").child("BasicProgramme");
            bref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                       CodeModel dataModel=new CodeModel();
                        dataModel.setCodetitle(snapshot.child("codetitle").getValue(String.class));
                        dataModel.setCode(snapshot.child("code").getValue(String.class));
                        dataModel.setOutput(snapshot.child("output").getValue(String.class));
                        dataModel.setCodePic(snapshot.child("codePic").getValue(String.class));
                        dataModel.setDescription(snapshot.child("description").getValue(String.class));
                        dataModel.setOutputPic(snapshot.child("outputPic").getValue(String.class));
                        dataModel.setYoutuvelink(snapshot.child("youtuve").getValue(String.class));
                        list.add(dataModel);
                    }
                    LinearLayoutManager layoutManagerban=new LinearLayoutManager(CodeTitleShow.this);
                    layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManagerban);
                    codeAdapter=new CodeAdapter(CodeTitleShow.this,list);
                    recyclerView.setAdapter(codeAdapter);
                    progressbar.setVisibility(View.INVISIBLE);
                    codeAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        // Control Statement
        if (value.contains("Control Statement")){

            progressbar.setVisibility(View.VISIBLE);
            DatabaseReference bref=reference.child("Programme").child("Control Statement");
            bref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                        CodeModel dataModel=new CodeModel();
                        dataModel.setCodetitle(snapshot.child("codetitle").getValue(String.class));
                        dataModel.setCode(snapshot.child("code").getValue(String.class));
                        dataModel.setOutput(snapshot.child("output").getValue(String.class));
                        dataModel.setCodePic(snapshot.child("codePic").getValue(String.class));
                        dataModel.setDescription(snapshot.child("description").getValue(String.class));
                        dataModel.setOutputPic(snapshot.child("outputPic").getValue(String.class));
                        dataModel.setYoutuvelink(snapshot.child("youtuve").getValue(String.class));
                        list.add(dataModel);
                    }
                    LinearLayoutManager layoutManagerban=new LinearLayoutManager(CodeTitleShow.this);
                    layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManagerban);
                    codeAdapter=new CodeAdapter(CodeTitleShow.this,list);
                    recyclerView.setAdapter(codeAdapter);
                    progressbar.setVisibility(View.INVISIBLE);
                    codeAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        // function
        if (value.contains("Function")){

            progressbar.setVisibility(View.VISIBLE);
            DatabaseReference bref=reference.child("Programme").child("Function");
            bref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                        CodeModel dataModel=new CodeModel();
                        dataModel.setCodetitle(snapshot.child("codetitle").getValue(String.class));
                        dataModel.setCode(snapshot.child("code").getValue(String.class));
                        dataModel.setOutput(snapshot.child("output").getValue(String.class));
                        dataModel.setCodePic(snapshot.child("codePic").getValue(String.class));
                        dataModel.setDescription(snapshot.child("description").getValue(String.class));
                        dataModel.setOutputPic(snapshot.child("outputPic").getValue(String.class));
                        dataModel.setYoutuvelink(snapshot.child("youtuve").getValue(String.class));
                        list.add(dataModel);
                    }
                    LinearLayoutManager layoutManagerban=new LinearLayoutManager(CodeTitleShow.this);
                    layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManagerban);
                    codeAdapter=new CodeAdapter(CodeTitleShow.this,list);
                    recyclerView.setAdapter(codeAdapter);
                    progressbar.setVisibility(View.INVISIBLE);
                    codeAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        // Loop Statement

        if (value.contains("Loop")){

            progressbar.setVisibility(View.VISIBLE);
            DatabaseReference bref=reference.child("Programme").child("Loop");
            bref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                        CodeModel dataModel=new CodeModel();
                        dataModel.setCodetitle(snapshot.child("codetitle").getValue(String.class));
                        dataModel.setCode(snapshot.child("code").getValue(String.class));
                        dataModel.setOutput(snapshot.child("output").getValue(String.class));
                        dataModel.setCodePic(snapshot.child("codePic").getValue(String.class));
                        dataModel.setDescription(snapshot.child("description").getValue(String.class));
                        dataModel.setOutputPic(snapshot.child("outputPic").getValue(String.class));
                        dataModel.setYoutuvelink(snapshot.child("youtuve").getValue(String.class));
                        list.add(dataModel);
                    }
                    LinearLayoutManager layoutManagerban=new LinearLayoutManager(CodeTitleShow.this);
                    layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManagerban);
                    codeAdapter=new CodeAdapter(CodeTitleShow.this,list);
                    recyclerView.setAdapter(codeAdapter);
                    progressbar.setVisibility(View.INVISIBLE);
                    codeAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        // Array
        if (value.contains("Array")){

            progressbar.setVisibility(View.VISIBLE);
            DatabaseReference bref=reference.child("Programme").child("Array");
            bref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                        CodeModel dataModel=new CodeModel();
                        dataModel.setCodetitle(snapshot.child("codetitle").getValue(String.class));
                        dataModel.setCode(snapshot.child("code").getValue(String.class));
                        dataModel.setOutput(snapshot.child("output").getValue(String.class));
                        dataModel.setCodePic(snapshot.child("codePic").getValue(String.class));
                        dataModel.setDescription(snapshot.child("description").getValue(String.class));
                        dataModel.setOutputPic(snapshot.child("outputPic").getValue(String.class));
                        dataModel.setYoutuvelink(snapshot.child("youtuve").getValue(String.class));
                        dataModel.setYoutuvelink(snapshot.child("youtuve").getValue(String.class));
                        list.add(dataModel);
                    }
                    LinearLayoutManager layoutManagerban=new LinearLayoutManager(CodeTitleShow.this);
                    layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManagerban);
                    codeAdapter=new CodeAdapter(CodeTitleShow.this,list);
                    recyclerView.setAdapter(codeAdapter);
                    progressbar.setVisibility(View.INVISIBLE);
                    codeAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        // String
        if (value.contains("String")){

            progressbar.setVisibility(View.VISIBLE);
            DatabaseReference bref=reference.child("Programme").child("String");
            bref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                        CodeModel dataModel=new CodeModel();
                        dataModel.setCodetitle(snapshot.child("codetitle").getValue(String.class));
                        dataModel.setCode(snapshot.child("code").getValue(String.class));
                        dataModel.setOutput(snapshot.child("output").getValue(String.class));
                        dataModel.setCodePic(snapshot.child("codePic").getValue(String.class));
                        dataModel.setDescription(snapshot.child("description").getValue(String.class));
                        dataModel.setOutputPic(snapshot.child("outputPic").getValue(String.class));
                        dataModel.setYoutuvelink(snapshot.child("youtuve").getValue(String.class));
                        list.add(dataModel);
                    }
                    LinearLayoutManager layoutManagerban=new LinearLayoutManager(CodeTitleShow.this);
                    layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManagerban);
                    codeAdapter=new CodeAdapter(CodeTitleShow.this,list);
                    recyclerView.setAdapter(codeAdapter);
                    progressbar.setVisibility(View.INVISIBLE);
                    codeAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        // Structure Union
        if (value.contains("Structure Union")){

            progressbar.setVisibility(View.VISIBLE);
            DatabaseReference bref=reference.child("Programme").child("Structure Union");
            bref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                        CodeModel dataModel=new CodeModel();
                        dataModel.setCodetitle(snapshot.child("codetitle").getValue(String.class));
                        dataModel.setCode(snapshot.child("code").getValue(String.class));
                        dataModel.setOutput(snapshot.child("output").getValue(String.class));
                        dataModel.setCodePic(snapshot.child("codePic").getValue(String.class));
                        dataModel.setDescription(snapshot.child("description").getValue(String.class));
                        dataModel.setOutputPic(snapshot.child("outputPic").getValue(String.class));
                        dataModel.setYoutuvelink(snapshot.child("youtuve").getValue(String.class));
                        list.add(dataModel);
                    }
                    LinearLayoutManager layoutManagerban=new LinearLayoutManager(CodeTitleShow.this);
                    layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManagerban);
                    codeAdapter=new CodeAdapter(CodeTitleShow.this,list);
                    recyclerView.setAdapter(codeAdapter);
                    progressbar.setVisibility(View.INVISIBLE);
                    codeAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        // Pointer
        if (value.contains("Pointer")){

            progressbar.setVisibility(View.VISIBLE);
            DatabaseReference bref=reference.child("Programme").child("Pointer");
            bref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                        CodeModel dataModel=new CodeModel();
                        dataModel.setCodetitle(snapshot.child("codetitle").getValue(String.class));
                        dataModel.setCode(snapshot.child("code").getValue(String.class));
                        dataModel.setOutput(snapshot.child("output").getValue(String.class));
                        dataModel.setCodePic(snapshot.child("codePic").getValue(String.class));
                        dataModel.setDescription(snapshot.child("description").getValue(String.class));
                        dataModel.setOutputPic(snapshot.child("outputPic").getValue(String.class));
                        dataModel.setYoutuvelink(snapshot.child("youtuve").getValue(String.class));
                        list.add(dataModel);
                    }
                    LinearLayoutManager layoutManagerban=new LinearLayoutManager(CodeTitleShow.this);
                    layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManagerban);
                    codeAdapter=new CodeAdapter(CodeTitleShow.this,list);
                    recyclerView.setAdapter(codeAdapter);
                    progressbar.setVisibility(View.INVISIBLE);
                    codeAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        // File Handling
        if (value.contains("File Handling")){

            progressbar.setVisibility(View.VISIBLE);
            DatabaseReference bref=reference.child("Programme").child("File Handling");
            bref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                        CodeModel dataModel=new CodeModel();
                        dataModel.setCodetitle(snapshot.child("codetitle").getValue(String.class));
                        dataModel.setCode(snapshot.child("code").getValue(String.class));
                        dataModel.setOutput(snapshot.child("output").getValue(String.class));
                        dataModel.setCodePic(snapshot.child("codePic").getValue(String.class));
                        dataModel.setDescription(snapshot.child("description").getValue(String.class));
                        dataModel.setOutputPic(snapshot.child("outputPic").getValue(String.class));
                        dataModel.setYoutuvelink(snapshot.child("youtuve").getValue(String.class));
                        list.add(dataModel);
                    }
                    LinearLayoutManager layoutManagerban=new LinearLayoutManager(CodeTitleShow.this);
                    layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManagerban);
                    codeAdapter=new CodeAdapter(CodeTitleShow.this,list);
                    recyclerView.setAdapter(codeAdapter);
                    progressbar.setVisibility(View.INVISIBLE);
                    codeAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
    private void checStatusBar(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            Window window=getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getSupportActionBar().hide();

        }

    }
}