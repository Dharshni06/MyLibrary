package com.example.mylibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnallbooks,btncurrent,btnalready,btnwishlist,btnfav,btnabt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnallbooks = findViewById(R.id.btnallbooks);
        btnallbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(in);
            }
        });
        Utils.getInstance(this);

        btnalready = findViewById(R.id.btnallready);
        btnalready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AlreadyReadbookActivity.class);
                startActivity(intent);
            }
        });

        btncurrent = findViewById(R.id.btncurrent);
        btncurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CurrentlyReadbookActivity.class);
                startActivity(intent);
            }
        });

        btnwishlist = findViewById(R.id.btnwishlist);
        btnwishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WanttoreadActivity.class);
                startActivity(intent);
            }
        });

        btnfav = findViewById(R.id.btnfav);
        btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FavouriteBookActivity.class);
                startActivity(intent);
            }
        });

        btnabt = findViewById(R.id.btnabt);
        btnabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle(getString(R.string.app_name));
                build.setMessage("Designed and Developed with Love by Dharshni at "+
                        "Check my Website for more information");

                build.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                build.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this,WebviewActivity.class);
                        intent.putExtra("url","https://google.com/");
                        startActivity(intent);
                    }
                });
                build.create().show();
            }
        });
    }
}