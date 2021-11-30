package com.programing.cprograming.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.programing.cprograming.CodeViewActivity;
import com.programing.cprograming.IntroduceDetailsActivity;
import com.programing.cprograming.Model.CodeModel;
import com.programing.cprograming.Model.DataModel;
import com.programing.cprograming.R;

import java.util.List;

import ng.max.slideview.SlideView;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.PlateViewHolder> {

    private Context context;
    private List<CodeModel> list;


    public CodeAdapter(Context context,List<CodeModel> list) {
        this.context = context;
        this.list = list;

    }


    @NonNull
    @Override


    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coderecyclerdesign,parent,false);
        return new PlateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder holder, final int position) {
        final CodeModel dataModel=list.get(position);
        holder.textView.setText(dataModel.getCodetitle());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to a new activity
                Intent intent=new Intent(context, CodeViewActivity.class);
                String g=dataModel.getCodetitle();
                intent.putExtra("codeTitle",g);
                String ff=dataModel.getCode();
                intent.putExtra("code",ff);
                intent.putExtra("output",dataModel.getOutput());
                intent.putExtra("codePic",dataModel.getCodePic());
                intent.putExtra("outputPic",dataModel.getOutputPic());
                intent.putExtra("description",dataModel.getDescription());
                intent.putExtra("youtuve",dataModel.getYoutuvelink());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PlateViewHolder extends RecyclerView.ViewHolder {

        CardView img;
        TextView textView;
        public PlateViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(CardView) itemView.findViewById(R.id.txtslidecode);
            textView=(TextView)itemView.findViewById(R.id.txttitle);
        }
    }
}