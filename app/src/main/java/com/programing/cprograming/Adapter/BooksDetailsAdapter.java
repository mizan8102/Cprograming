package com.programing.cprograming.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.programing.cprograming.PdfShowActivity;
import com.programing.cprograming.R;

import java.util.ArrayList;

public class BooksDetailsAdapter extends RecyclerView.Adapter<BooksDetailsAdapter.MyViewHolder> {
    private Context context;
    private String [] tilte;
    private int [] titlepic;
    private String [] pdf;
    public BooksDetailsAdapter(Context context, String[] tilte, int[] titlepic, String[] pdf) {
        this.context = context;
        this.tilte = tilte;
        this.titlepic = titlepic;
        this.pdf = pdf;
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater layoutInflater=new
        View view= LayoutInflater.from(context).inflate(R.layout.book_recycler_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            holder.textView.setText(tilte[position]);
            Glide.with(context)
                    .load(titlepic[position]).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PdfShowActivity.class);
                intent.putExtra("bookid",pdf[position]);
                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return tilte.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.booktitlepic);
            textView=itemView.findViewById(R.id.bookstitle);

        }
    }

}