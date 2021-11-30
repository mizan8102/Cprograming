package com.programing.cprograming.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.programing.cprograming.IntroduceDetailsActivity;
import com.programing.cprograming.IntroductionActivity;
import com.programing.cprograming.Model.DataModel;
import com.programing.cprograming.R;

import java.util.List;

import ng.max.slideview.SlideView;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.PlateViewHolder> {

    private Context context;
    private List<DataModel> list;


    public RecycleAdapter(Context context,List<DataModel> list) {
        this.context = context;
        this.list = list;

    }


    @NonNull
    @Override


    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_design,parent,false);
        return new PlateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder holder, final int position) {
        final DataModel dataModel=list.get(position);
        holder.img.setText(dataModel.getTitle());
        holder.img.setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideView slideView) {
                Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);

                // go to a new activity
                Intent intent=new Intent(context, IntroduceDetailsActivity.class);
                String g=dataModel.getTitle();
                intent.putExtra("c_info",g);
                String ff=dataModel.getDescription();
                intent.putExtra("c_def",ff);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PlateViewHolder extends RecyclerView.ViewHolder {

        SlideView img;
        public PlateViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(SlideView) itemView.findViewById(R.id.txtdata);
        }
    }
}