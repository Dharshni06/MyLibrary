package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    private TextView txtnameofbook, txtnameofauthor, txtpageno,txtlongdesc;
    private Button btnaddcurrent, btnaddalready, btnaddcurrently,btnaddfavourite;
    private ImageView img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        txtnameofbook = findViewById(R.id.txtnameofbook);
        txtnameofauthor = findViewById(R.id.txtnameofauthor);
        txtpageno = findViewById(R.id.txtpageno);
        txtlongdesc = findViewById(R.id.txtlongdesc);

        btnaddcurrent = findViewById(R.id.btnaddcurrent);
        btnaddalready = findViewById(R.id.btnaddalready);
        btnaddcurrently = findViewById(R.id.btnaddwantto);
        btnaddfavourite = findViewById(R.id.btnaddfavourite);

        img2 = findViewById(R.id.img2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        String txtlongdesc = "It is set in a world where humanity lives inside cities surrounded by three enormous walls that protect them from the gigantic man eating humanoids referred to as Titans; the story follows Eren Yaeger who vows to exterminate the titan after a titan brings about the destruction of his hometown and the death of his mother ";
//
//        Book book= new Book(1,"Attack on Titan","Hajime Isayama",6880,"https://static.wikia.nocookie.net/shingekinokyojin/images/b/bd/AOT_Season_2_Keyart.jpg/revision/latest/scale-to-width-down/250?cb=20200925193709",
//                "Action,Dark Fantasy",txtlongdesc);

        Intent intent =getIntent();
        if(null != intent){
            int bookId = intent.getIntExtra("bookId",-1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if(null != incomingBook){
                    setData(incomingBook);

                    handlealready(incomingBook);
                    handlewantto(incomingBook);
                    handlecurrently(incomingBook);
                    handlefav(incomingBook);
                }
            }
        }
    }
    private void handlealready(Book book){
        ArrayList<Book> alreadyreadbook = Utils.getInstance(this).getAlreadyread();

        boolean existinAlready = false;
        for(Book b : alreadyreadbook){
            if(b.getId() == book.getId()){
                existinAlready = true;
            }
        }

        if(existinAlready){
            btnaddalready.setEnabled(false);
        }
        else{
            btnaddalready.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addtoalready(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,AlreadyReadbookActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handlewantto(final Book book){
        ArrayList<Book> wanttoread = Utils.getInstance(this).getWanttoread();

        boolean existinwantto = false;
        for(Book b : wanttoread){
            if(b.getId() == book.getId()){
                existinwantto = true;
            }
        }

        if(existinwantto){
            btnaddcurrently.setEnabled(false);
        }
        else{
            btnaddcurrently.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addcurrently(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WanttoreadActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handlecurrently(final Book book){
        ArrayList<Book> currently = Utils.getInstance(this).getCurrentreading();

        boolean existcurrently = false;
        for(Book b : currently){
            if(b.getId() == book.getId()){
                existcurrently = true;
            }
        }

        if(existcurrently){
            btnaddcurrently.setEnabled(false);
        }
        else{
            btnaddcurrently.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addcurrently(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadbookActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handlefav(final Book book){
        ArrayList<Book> favourite = Utils.getInstance(this).getFavouritebook();

        boolean existinfav = false;
        for(Book b : favourite){
            if(b.getId() == book.getId()){
                existinfav = true;
            }
        }

        if(existinfav){
            btnaddfavourite.setEnabled(false);
        }
        else{
            btnaddfavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addfav(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,FavouriteBookActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void setData(Book book){
        txtnameofbook.setText(book.getName());
        txtnameofauthor.setText(book.getAuthor());
        txtpageno.setText(String.valueOf(book.getPages()));
        txtlongdesc.setText(book.getLongDesc());

        Glide.with(this).asBitmap().load(book.getImageurl()).into(img2);
    }

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