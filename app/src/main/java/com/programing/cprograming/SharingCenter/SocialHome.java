package com.programing.cprograming.SharingCenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.programing.cprograming.Adapter.RecycleAdapter;
import com.programing.cprograming.Adapter.SocialAdapter;
import com.programing.cprograming.BiginnerTips;
import com.programing.cprograming.Model.DataModel;
import com.programing.cprograming.Model.SocialModel;
import com.programing.cprograming.R;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class SocialHome extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;
    private DatabaseReference reference;
    private List<SocialModel> list;
    private SocialAdapter socialAdapter;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private GifImageView progressbard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_home);
        getSupportActionBar().setTitle("Community");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionButton=(FloatingActionButton)findViewById(R.id.fab);
       recyclerView=(RecyclerView)findViewById(R.id.allpostsocial);
        progressbard=(GifImageView)findViewById(R.id.Postload) ;

      SharedPreferences sp = getApplicationContext().getSharedPreferences
                ("com.programing.cprograming", Context.MODE_PRIVATE);

        String id = sp.getString("user_id", "");
        reference= FirebaseDatabase.getInstance().getReference().child("ProblemPost");

        list=new ArrayList<>();
       reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressbard.setVisibility(View.VISIBLE);
                list.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    SocialModel dataModel=new SocialModel();
                    dataModel.setuPic(snapshot.child("uPic").getValue(String.class));
                    dataModel.setuName(snapshot.child("uName").getValue(String.class));
                    dataModel.setPostPic(snapshot.child("postImage").getValue(String.class));
                    dataModel.setPostText(snapshot.child("write").getValue(String.class));
                    dataModel.setMuid(snapshot.child("postId").getValue(String.class));
                    list.add(dataModel);
                }
                //Toast.makeText(SocialHome.this,list.toString(),Toast.LENGTH_LONG).show();
                LinearLayoutManager layoutManagerban=new LinearLayoutManager(SocialHome.this);
                layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(layoutManagerban);
              socialAdapter=new SocialAdapter(SocialHome.this,list);
                recyclerView.setAdapter(socialAdapter);
                progressbard.setVisibility(View.INVISIBLE);
               socialAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        actionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(SocialHome.this,ProblemPostActivity.class));
               Animatoo.animateSlideUp(SocialHome.this);

           }
       });
        setMySwipeRefreshLayout();

    }

    final void setMySwipeRefreshLayout(){
        mySwipeRefreshLayout = findViewById(R.id.swipeContainer);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    final public void onRefresh() {
                       hello();
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
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

    private void hello(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressbard.setVisibility(View.VISIBLE);
                list.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    SocialModel dataModel=new SocialModel();
                    dataModel.setuPic(snapshot.child("uPic").getValue(String.class));
                    dataModel.setuName(snapshot.child("uName").getValue(String.class));
                    dataModel.setPostPic(snapshot.child("postImage").getValue(String.class));
                    dataModel.setPostText(snapshot.child("write").getValue(String.class));
                    dataModel.setMuid(snapshot.child("postId").getValue(String.class));
                    list.add(dataModel);
                }
                //Toast.makeText(SocialHome.this,list.toString(),Toast.LENGTH_LONG).show();
                LinearLayoutManager layoutManagerban=new LinearLayoutManager(SocialHome.this);
                layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(layoutManagerban);
                socialAdapter=new SocialAdapter(SocialHome.this,list);
                recyclerView.setAdapter(socialAdapter);
                progressbard.setVisibility(View.INVISIBLE);
                socialAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}