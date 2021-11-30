package com.programing.cprograming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.programing.cprograming.Adapter.BooksDetailsAdapter;

public class BookDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BooksDetailsAdapter adapter;
    private String title[]={
        "Learn to Program with C\n Noel Kalicharan","Programing C \n Tutorials Point","GNC Programing\nMark Burgess","কম্পিউটার প্রোগ্রামিংঃ১ম খণ্ড\n তামিম শাহরিয়ার সুবিন"


    };
    private int [] titlepic={
            R.drawable.bookone,R.drawable.booktwo,R.drawable.bookthree,R.drawable.bookfour

    };

    private String [] pdf={
         "bookone.pdf","booktwo.pdf","booktthree.pdf","bookfour.pdf"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        String value = getIntent().getExtras().getString("key");
        getSupportActionBar().setTitle(value);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView=(RecyclerView)findViewById(R.id.bookrecycle);
        recyclerView.setHasFixedSize(true);
        if (!(title.length<1)){
           adapter=new BooksDetailsAdapter(this,title,titlepic,pdf);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));


        }
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
}