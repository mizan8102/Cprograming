package com.programing.cprograming.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.programing.cprograming.IntroduceDetailsActivity;
import com.programing.cprograming.Model.CommentModel;
import com.programing.cprograming.Model.DataModel;
import com.programing.cprograming.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ng.max.slideview.SlideView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.PlateViewHolder> {

    private Context context;
    private List<CommentModel> list;


    public CommentAdapter(Context context,List<CommentModel> list) {
        this.context = context;
        this.list = list;

    }


    @NonNull
    @Override


    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.commentrecycler,parent,false);
        return new PlateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder holder, final int position) {
        final CommentModel dataModel=list.get(position);
        if (!(holder.uname==null)){
            holder.uname.setText(dataModel.getName());
            holder.textView.setText(dataModel.getComment());

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PlateViewHolder extends RecyclerView.ViewHolder {

        TextView textView,uname;


        public PlateViewHolder(@NonNull View itemView) {
            super(itemView);
           textView=itemView.findViewById(R.id.txtcomment);
           uname=itemView.findViewById(R.id.idname);

        }
    }
}