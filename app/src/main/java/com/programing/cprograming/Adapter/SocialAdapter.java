package com.programing.cprograming.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.programing.cprograming.IntroduceDetailsActivity;
import com.programing.cprograming.Model.DataModel;
import com.programing.cprograming.Model.SocialModel;
import com.programing.cprograming.R;
import com.programing.cprograming.SharingCenter.CommentActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ng.max.slideview.SlideView;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.PlateViewHolder> {

    private Context context;
    private List<SocialModel> list;


    public SocialAdapter(Context context,List<SocialModel> list) {
        this.context = context;
        this.list = list;

    }


    @NonNull
    @Override


    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sharingrecycler,parent,false);
        return new PlateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder holder, final int position) {
        final SocialModel dataModel=list.get(position);
        holder.uname.setText(dataModel.getuName());
        holder.postDes.setText(dataModel.getPostText());
        //Glide.with(context).load(dataModel.getuPic()).placeholder(R.drawable.info).into(holder.circleImageView);

        String m=dataModel.getPostPic();
        if (!(m=="")){
            Glide.with(context).load(dataModel.getPostPic()).into(holder.postImage);
        }else {
            holder.postImage.setVisibility(View.INVISIBLE);
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(context,CommentActivity.class);
                intent.putExtra("header",dataModel.getMuid());
              context.startActivity(intent);
                Animatoo.animateSlideDown(context);
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PlateViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        ImageView postImage;
        TextView uname;
        TextView postDes;
        Button button;
        public PlateViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView=(CircleImageView)itemView.findViewById(R.id.socialphoto);
            postImage=(ImageView)itemView.findViewById(R.id.postphoto);
            uname=(TextView)itemView.findViewById(R.id.socialname);
            postDes=(TextView)itemView.findViewById(R.id.postdescription);
            button=(Button)itemView.findViewById(R.id.txtsolutionbtb);
        }
    }
}
