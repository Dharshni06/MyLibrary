package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    private BooksRecViewAdapter adapter;
    private RecyclerView booksrecview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        //overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        booksrecview = findViewById(R.id.booksrecview);
        adapter= new BooksRecViewAdapter(this,"allbooks");

        booksrecview.setAdapter(adapter);
        booksrecview.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getAllbooks());
    }

    /*@Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}