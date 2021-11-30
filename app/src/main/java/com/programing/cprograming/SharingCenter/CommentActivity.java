package com.programing.cprograming.SharingCenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.programing.cprograming.Adapter.CommentAdapter;
import com.programing.cprograming.Adapter.SocialAdapter;
import com.programing.cprograming.Model.CommentModel;
import com.programing.cprograming.Model.SocialModel;
import com.programing.cprograming.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CommentActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Button button;
    EditText editText;
    DatabaseReference databaseReference,datref;
    private static String QuestionId;
    private String name;
    private ProgressDialog progressDialog;
    List<CommentModel> list;
    private CommentAdapter commentAdapter;
    private String id;
    private String comid;
    private int NOTIFICATION_ID=12;

    boolean notify=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        recyclerView=(RecyclerView)findViewById(R.id.commentsoluation);
        editText=(EditText)findViewById(R.id.solutioncomment);
        button=(Button)findViewById(R.id.btnaddcomment);
        getSupportActionBar().setTitle("Solution");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Comment");
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        SharedPreferences sp=getSharedPreferences
                ("com.programing.cprograming", Context.MODE_PRIVATE);
        name = sp.getString("user_name", "");
        id = sp.getString("user_id", "");



        Intent intent=getIntent();
        QuestionId=intent.getStringExtra("header");
        Datashow();




        //Toast.makeText(this,QuestionId,Toast.LENGTH_LONG).show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                notify=true;
                DatabaseReference dref=databaseReference.child(QuestionId).child(String.valueOf(UUID.randomUUID()));
                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("solution",editText.getText().toString());
                hashMap.put("comname",name);
                hashMap.put("comUserid",id);
                dref.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CommentActivity.this,"Comment Succesfull",Toast.LENGTH_SHORT).show();
                        Datashow();
                        editText.setText("");
                    }
                });
                dref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            notification();


                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        notification();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }
    public void Datashow() {
        DatabaseReference dtf=databaseReference.child(QuestionId);
        dtf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){



                   CommentModel dataModel=new CommentModel();
                    dataModel.setName(snapshot.child("comname").getValue(String.class));
                    dataModel.setComment(snapshot.child("solution").getValue(String.class));
                    comid=snapshot.child("comUserid").getValue(String.class);
                    list.add(dataModel);
                }
               //Toast.makeText(CommentActivity.this,list.toString(),Toast.LENGTH_LONG).show();
                LinearLayoutManager layoutManagerban=new LinearLayoutManager(CommentActivity.this);
                layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(layoutManagerban);
               commentAdapter=new CommentAdapter(CommentActivity.this,list);
               notification();
                recyclerView.setAdapter(commentAdapter);

               commentAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

    private void notification(){


        String message = "solution of your problem";
        Intent intent = new Intent(this,CommentActivity.class);
        intent.putExtra("header", QuestionId);

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"n")

                .setContentTitle("Coding Hero")
                .setSmallIcon(R.drawable.logoc)
                .setContentIntent(pIntent)
                .setContentText(message)
                .setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID,builder.build());
    }
}