package com.programing.cprograming.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.programing.cprograming.Adapter.SocialAdapter;
import com.programing.cprograming.Model.SocialModel;
import com.programing.cprograming.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private View view;
    private CircleImageView txtimage;
    private TextView emailT, addresT, nameT;
    private CoordinatorLayout picdraw;
    private Dialog dialog;
    int CAMERA_REQUEST = 12;
    int SELECT_REQUEST = 10;
    private List<SocialModel> list;
    private SocialAdapter socialAdapter;
    private Uri pathfile;
    private String id;
    FirebaseDatabase database ;
   StorageReference mStorage;
   private  String name;
   private SharedPreferences sp;
    private ProgressDialog progressDialog;
    private RecyclerView ownPost;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);
        txtimage = view.findViewById(R.id.txtaccountImage);
        emailT = view.findViewById(R.id.txtemailaccount);
        nameT = view.findViewById(R.id.txtidname);
        picdraw = view.findViewById(R.id.txtaddpic);
        ownPost=view.findViewById(R.id.ownPost);
       mStorage=FirebaseStorage.getInstance().getReference();
        database= FirebaseDatabase.getInstance();
        list=new ArrayList<>();
        picdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogueWork();
            }
        });
        reference= FirebaseDatabase.getInstance().getReference().child("ProblemPost");

        sp = getActivity().getApplicationContext().getSharedPreferences
                ("com.programing.cprograming", Context.MODE_PRIVATE);
        name = sp.getString("user_name", "");
        String email = sp.getString("user_email", "");
        String picUrl = sp.getString("imageUrl", "");
         id = sp.getString("user_id", "");
        nameT.setText(name);
        emailT.setText(email);
        if (TextUtils.isEmpty(picUrl)) {
            Glide.with(view).load(R.drawable.info).placeholder(R.drawable.info).into(txtimage);

        } else {
            Glide.with(view).load(picUrl).placeholder(R.drawable.info).into(txtimage);
        }
        if(!(TextUtils.isEmpty(email))){
            ShowData();
        }

        return view;
    }

    private LinearLayout camerabtn,selectbtn;
    void dialogueWork(){
        dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialoguedesign);
        dialog.show();
        camerabtn=dialog.findViewById(R.id.txtfromcamera);
        selectbtn=dialog.findViewById(R.id.txtfromgallery);
        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        selectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectIntent=new Intent();
                selectIntent.setType("image/*");
                selectIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(selectIntent,SELECT_REQUEST);
            }
        });




    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST){
            pathfile=data.getData();
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            txtimage.setImageBitmap(bitmap);
            UploadData();
            dialog.dismiss();
        }
        else if (requestCode==SELECT_REQUEST){
            pathfile=data.getData();
            Bitmap bitmap= null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),pathfile);
               txtimage.setImageBitmap(bitmap);
                UploadData();
                dialog.dismiss();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            Toast.makeText(getContext(),"File cann't find",Toast.LENGTH_SHORT).show();
        }
    }


    private void UploadData(){
        if (pathfile!=null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("uploading....");
            progressDialog.show();
            final StorageReference storageReference = mStorage.child("User/" + UUID.randomUUID());
            storageReference.putFile(pathfile)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(final Uri uri) {
                                    final DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
                                    DatabaseReference df = firebaseDatabase.child(id);
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("id", id);
                                    hashMap.put("name", name);
                                    hashMap.put("institute", "");
                                    hashMap.put("imagUrl", uri.toString());
                                    df.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            sp.edit().putString("imageUrl", uri.toString()).apply();
                                            Toast.makeText(getContext(), "Succesful ", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });

                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    // Error, Image not uploaded


                }
            })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");

                        }
                    });
        }

    }


    private void ShowData() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // progressbard.setVisibility(View.VISIBLE);
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    SocialModel dataModel = new SocialModel();
                    dataModel.setuPic(snapshot.child("uPic").getValue(String.class));
                    dataModel.setuName(snapshot.child("uName").getValue(String.class));
                    dataModel.setPostPic(snapshot.child("postImage").getValue(String.class));
                    dataModel.setPostText(snapshot.child("write").getValue(String.class));
                    dataModel.setMuid(snapshot.child("postId").getValue(String.class));
                    String gh = (snapshot.child("id").getValue(String.class));

                    if (gh.contains(id)) {
                        list.add(dataModel);
                    }

                }
                //Toast.makeText(SocialHome.this,list.toString(),Toast.LENGTH_LONG).show();
                LinearLayoutManager layoutManagerban = new LinearLayoutManager(getContext());
                layoutManagerban.setOrientation(RecyclerView.VERTICAL);
                ownPost.setLayoutManager(layoutManagerban);
                socialAdapter = new SocialAdapter(getContext(), list);
                ownPost.setAdapter(socialAdapter);
                //progressbard.setVisibility(View.INVISIBLE);
                socialAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    }



