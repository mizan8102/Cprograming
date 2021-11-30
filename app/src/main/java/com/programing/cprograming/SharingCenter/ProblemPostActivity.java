package com.programing.cprograming.SharingCenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.inputmethodservice.ExtractEditText;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.programing.cprograming.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import es.dmoral.toasty.Toasty;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class ProblemPostActivity extends AppCompatActivity {

    Button gallary,camera;
    ExtendedEditText description;
    ImageView proPic;
    int CAMERA_REQUEST = 12;
    int SELECT_REQUEST = 10;
    private Uri pathfile;
    private ProgressBar progressBar;
    private Button button;
    private String id;
    private ProgressDialog progressDialog;

    private DatabaseReference dref;
    private StorageReference mstorage;
    private String name,picUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_post);
        getSupportActionBar().setTitle("Share Problem");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gallary=(Button)findViewById(R.id.fromselectgallary);
        camera=(Button)findViewById(R.id.fromselectcamera);
        description=(ExtendedEditText)findViewById(R.id.extended_edit_text);
        proPic=(ImageView)findViewById(R.id.postactivitypic);
        button=(Button)findViewById(R.id.postsave);
        mstorage= FirebaseStorage.getInstance().getReference();
        dref= FirebaseDatabase.getInstance().getReference().child("ProblemPost");

        // gallary button work
        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectIntent=new Intent();
                selectIntent.setType("image/*");
                selectIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(selectIntent,SELECT_REQUEST);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,SELECT_REQUEST);
            }
        });
        ///DatabaseReference reference=dref.child(id).child(UUID.randomUUID().toString());
        SharedPreferences sp=getSharedPreferences
                ("com.programing.cprograming", Context.MODE_PRIVATE);
        id = sp.getString("user_id", "");

        name = sp.getString("user_name", "");

         picUrl = sp.getString("imageUrl", "");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pathfile != null) {

                    progressDialog = new ProgressDialog(ProblemPostActivity.this);
                    progressDialog.setTitle("uploading....");
                    progressDialog.show();
                    final StorageReference storageReference = mstorage.child("ProblemPost/" + UUID.randomUUID());
                    storageReference.putFile(pathfile)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            final DatabaseReference firebaseDatabase = dref;
                                            String muid=String.valueOf(UUID.randomUUID());
                                            DatabaseReference df = firebaseDatabase.child(muid);
                                            HashMap<String, String> hashMap = new HashMap<>();
                                            hashMap.put("id",id);
                                            hashMap.put("postId",muid);
                                            hashMap.put("uPic",picUrl);
                                            hashMap.put("uName",name);
                                            hashMap.put("postImage", String.valueOf(uri));
                                            hashMap.put("write", description.getText().toString());
                                            df.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toasty.info(ProblemPostActivity.this, "Posting Successful..", Toast.LENGTH_SHORT, true).show();
                                                    progressDialog.dismiss();
                                                    startActivity(new Intent(ProblemPostActivity.this,
                                                            SocialHome.class));
                                                    finish();
                                                    Animatoo.animateSlideLeft(ProblemPostActivity.this);

                                                }
                                            });

                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toasty.error(ProblemPostActivity.this,"No Internet Connection!").show();
                        }
                    })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                                }
                            });

                } else {
                    //Toasty.error(ProblemPostActivity.this,"File cann't find!").show();

                    final DatabaseReference firebaseDatabase = dref;
                    String muid=String.valueOf(UUID.randomUUID());
                    DatabaseReference df = firebaseDatabase.child(muid);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id",id);
                    hashMap.put("postId",muid);
                    hashMap.put("uPic",picUrl);
                    hashMap.put("uName",name);
                    hashMap.put("postImage", "");
                    hashMap.put("write", description.getText().toString());
                    df.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                           Toasty.info(ProblemPostActivity.this, "Posting Successful..", Toast.LENGTH_SHORT, true).show();
                            //progressDialog.dismiss();
                            startActivity(new Intent(ProblemPostActivity.this,
                                    SocialHome.class));
                            finish();
                            Animatoo.animateSlideLeft(ProblemPostActivity.this);

                        }
                    });
                }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==CAMERA_REQUEST){
            pathfile=data.getData();
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            proPic.setImageBitmap(bitmap);
        }
        else if (requestCode==SELECT_REQUEST){
            pathfile=data.getData();
            Bitmap bitmap= null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),pathfile);
               proPic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            Toast.makeText(this,"File cann't find",Toast.LENGTH_SHORT).show();
        }
    }
}