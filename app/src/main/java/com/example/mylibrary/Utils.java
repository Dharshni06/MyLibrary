package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static String ALL_BOOKS_KEY = " allbooks";
    private static String ALREADY_READ_BOOK = "alreadyread";
    private static String CURRENTLY_READING = "currentlyreading";
    private static String WANT_TO_READ = "wanttoread";
    private static String FAV_BOOK_KEY = "favbooks";

    private static Utils instance;
    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allbooks;
//    private static ArrayList<Book> currentreading;
//    private static ArrayList<Book> alreadyread;
//    private static ArrayList<Book> wanttoread;
//    private static ArrayList<Book> favouritebook;

    public Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        if (null == getAllbooks()) {
            initdata();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getCurrentreading()) {
            editor.putString(CURRENTLY_READING, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getAlreadyread()) {
            editor.putString(ALREADY_READ_BOOK, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getWanttoread()) {
            editor.putString(WANT_TO_READ, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getFavouritebook()) {
            editor.putString(FAV_BOOK_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initdata() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "Attack on Titan", "Hajime Isayama", 6880, "https://static.wikia.nocookie.net/shingekinokyojin/images/b/bd/AOT_Season_2_Keyart.jpg/revision/latest/scale-to-width-down/250?cb=20200925193709",
                "Action,Dark Fantasy", "Compare to Real World"));

        books.add(new Book(2, "Demon Slayer", "Koyoharu Gotouge", 4290, "data:image/jpeg;base64,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",
                "Adventure, Material arts", "Long description"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();
    }

    public static Utils getInstance(Context context) {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Book> getAllbooks() {
        Gson gson = new Gson();
        Type typ = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), typ);
        return books;
    }

    public ArrayList<Book> getCurrentreading() {
        Gson gson = new Gson();
        Type typ = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING, null), typ);
        return books;
    }

    public ArrayList<Book> getAlreadyread() {
        Gson gson = new Gson();
        Type typ = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOK, null), typ);
        return books;
    }

    public ArrayList<Book> getWanttoread() {
        Gson gson = new Gson();
        Type typ = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ, null), typ);
        return books;
    }

    public ArrayList<Book> getFavouritebook() {
        Gson gson = new Gson();
        Type typ = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAV_BOOK_KEY, null), typ);
        return books;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllbooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addtoalready(Book book) {
        ArrayList<Book> books = getAlreadyread();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOK);
                editor.putString(ALREADY_READ_BOOK, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addwanttoread(Book book) {
        ArrayList<Book> books = getWanttoread();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ);
                editor.putString(WANT_TO_READ, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addcurrently(Book book) {
        ArrayList<Book> books = getCurrentreading();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING);
                editor.putString(CURRENTLY_READING, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addfav(Book book) {
        ArrayList<Book> books = getFavouritebook();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAV_BOOK_KEY);
                editor.putString(FAV_BOOK_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removealreadyread(Book book) {
        ArrayList<Book> books = getAlreadyread();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOK);
                        editor.putString(ALREADY_READ_BOOK, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removewantto(Book book) {
        ArrayList<Book> books = getWanttoread();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ);
                        editor.putString(WANT_TO_READ, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removecurrently(Book book) {
        ArrayList<Book> books = getCurrentreading();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING);
                        editor.putString(CURRENTLY_READING, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removefav(Book book) {
        ArrayList<Book> books = getFavouritebook();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAV_BOOK_KEY);
                        editor.putString(FAV_BOOK_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


