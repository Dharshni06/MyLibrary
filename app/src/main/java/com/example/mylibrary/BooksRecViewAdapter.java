package com.example.mylibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BooksRecViewAdapter extends RecyclerView.Adapter<BooksRecViewAdapter.ViewHolder> {
    private static final String TAG = "BooksRecViewAdapter";
    private ArrayList<Book> books = new ArrayList<>();
    private Context context;
    private String parentActivity;

    public BooksRecViewAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_books,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final  int position) {
        Log.d(TAG, "onBindViewHolder: stated");
        holder.bookname1.setText(books.get(position).getName());
        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageurl())
                .into(holder.img1);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,BookActivity.class);
                intent.putExtra("bookId",books.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.txtauthor.setText(books.get(position).getAuthor());
        holder.shortdesc.setText(books.get(position).getAuthor());

        if(books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandtxtRL.setVisibility(View.VISIBLE);
            holder.arrowdown.setVisibility(View.VISIBLE);

            if(parentActivity.equals("allbooks")){
                holder.txtdelete.setVisibility(View.GONE);
            }
            else if(parentActivity.equals("alreadyread")){
                holder.txtdelete.setVisibility(View.VISIBLE);
                holder.txtdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder build = new AlertDialog.Builder(context);
                        build.setMessage("Are you Sure want to delete "+books.get(position).getName()+"?");
                        build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(context).removealreadyread(books.get(position))){
                                    Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        build.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        build.create().show();
                    }
                });
            }

            else if(parentActivity.equals("wanttoread")){
                holder.txtdelete.setVisibility(View.VISIBLE);
                holder.txtdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder build = new AlertDialog.Builder(context);
                        build.setMessage("Are you Sure want to delete "+books.get(position).getName()+"?");
                        build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(context).removewantto(books.get(position))){
                                    Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        build.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        build.create().show();
                    }
                });
            }

            else if(parentActivity.equals("currentlyread")){
                holder.txtdelete.setVisibility(View.VISIBLE);
                holder.txtdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder build = new AlertDialog.Builder(context);
                        build.setMessage("Are you Sure want to delete "+books.get(position).getName()+"?");
                        build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(context).removecurrently(books.get(position))){
                                    Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        build.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        build.create().show();
                    }
                });
            }

            else if(parentActivity.equals("favouritebook")){
                holder.txtdelete.setVisibility(View.VISIBLE);
                holder.txtdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder build = new AlertDialog.Builder(context);
                        build.setMessage("Are you Sure want to delete "+books.get(position).getName()+"?");
                        build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(context).removefav(books.get(position))){
                                    Toast.makeText(context, "Book Removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        build.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        build.create().show();
                    }
                });
            }
        }
        else
        {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandtxtRL.setVisibility(View.VISIBLE);
            holder.arrowdown.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView img1;
        private TextView bookname1;
        
        private RelativeLayout expandtxtRL, imgRL;
        private TextView txtauthor, shortdesc,txtdelete;
        private ImageView arrowup, arrowdown;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            img1 = itemView.findViewById(R.id.img1);
            bookname1 = itemView.findViewById(R.id.bookname1);
            
            expandtxtRL = itemView.findViewById(R.id.expandtxtRL);
            imgRL = itemView.findViewById(R.id.imgRL);
            txtauthor = itemView.findViewById(R.id.txtauthor);
            shortdesc = itemView.findViewById(R.id.shortdesc);
            arrowdown = itemView.findViewById(R.id.downtbtn);
            arrowup = itemView.findViewById(R.id.upbtn);

            txtdelete= itemView.findViewById(R.id.txtdel);

            arrowdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            arrowup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
