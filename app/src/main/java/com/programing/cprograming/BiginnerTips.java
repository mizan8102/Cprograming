package com.programing.cprograming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.programing.cprograming.Adapter.RecycleAdapter;
import com.programing.cprograming.Model.DataModel;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class BiginnerTips extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecycleAdapter adapter;
    private TextView textView;
    private DatabaseReference reference;
    private GifImageView progressbar;
  List<DataModel> list;
  String m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biginner_tips);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewt);
        progressbar=(GifImageView)findViewById(R.id.loadingIcontest) ;
        String value = getIntent().getExtras().getString("key");
        list=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference();
        getSupportActionBar().setTitle(value);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        m=getIntent().getExtras().getString("array_des");



        if (m.contains("Beginer")){
            progressbar.setVisibility(View.VISIBLE);
            DatabaseReference bref=reference.child("BeginnerTips");
            bref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                        DataModel dataModel=new DataModel();
                        dataModel.setTitle(snapshot.child("title").getValue(String.class));
                        dataModel.setDescription(snapshot.child("description").getValue(String.class));
                        list.add(dataModel);
                    }
                    LinearLayoutManager layoutManagerban=new LinearLayoutManager(BiginnerTips.this);
                    layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManagerban);
                    adapter=new RecycleAdapter(BiginnerTips.this,list);
                    recyclerView.setAdapter(adapter);
                    progressbar.setVisibility(View.INVISIBLE);
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }
        if (m.contains("InterviewQuestion")){
            progressbar.setVisibility(View.VISIBLE);
            DatabaseReference bref=reference.child("InterviewQuestion");
            bref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                        DataModel dataModel=new DataModel();
                        dataModel.setTitle(snapshot.child("title").getValue(String.class));
                        dataModel.setDescription(snapshot.child("description").getValue(String.class));
                        list.add(dataModel);
                    }
                    LinearLayoutManager layoutManagerban=new LinearLayoutManager(BiginnerTips.this);
                    layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManagerban);
                    adapter=new RecycleAdapter(BiginnerTips.this,list);
                    recyclerView.setAdapter(adapter);
                    progressbar.setVisibility(View.INVISIBLE);
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}