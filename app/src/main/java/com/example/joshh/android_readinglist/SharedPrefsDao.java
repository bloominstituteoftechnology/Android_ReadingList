package com.example.joshh.android_readinglist;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

public class SharedPrefsDao {
    private static final String KEY_IDS = "key_ids";
    private static final String NEXT_KEY_ID = "key_next_id";


    private static String getIdsString(){
        String keyIds = "";
        if (MainActivity.preferences != null) {
            keyIds = MainActivity.preferences.getString(KEY_IDS, "");
        }
        return keyIds;
    }

    private static String[] getAllIds() {
        //Keys are stored as CSV string
        String[] ids = getIdsString().split(",");
        return ids;
    }

    public static ArrayList<Book> getAllBooks(){
        String[]  ids = getAllIds();
        ArrayList<Book> books = new ArrayList<>(ids.length);
        for(String id : ids){
            if(!id.equals(""))
                books.add(getBook(id));
        }
        return books;
    }

    public static Book getBook(String id) {
        Book book = null;
        if (MainActivity.preferences != null) {
            final String bookString = MainActivity.preferences.getString(id, "");
            book = new Book(bookString);
        }
        return book;
    }

    public static String getNextId(){
        int currentId = 0;
        if (MainActivity.preferences != null) {
            currentId = MainActivity.preferences.getInt(NEXT_KEY_ID, 0);
            int nextId = currentId + 1;
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            editor.putInt(NEXT_KEY_ID, nextId);
            editor.apply();
        }
        return Integer.toString(currentId);
    }

    public static void updateBook(Book book){
        if(book.getId() == null){
            book.setId(getNextId());
        }
        String[] ids = getAllIds();
        boolean exists = false;
        for(String id : ids){
            if(!id.equals("")){
                if(book.getId() == id){
                    exists = true;
                    break;
                }
            }
        }
        if(!exists){
            addId(book.getId());
        }
        addBook(book);
        Log.i("UPDATEBOOK", getIdsString());
    }

    private static void addBook(Book book){
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(book.getId(), book.toCsvString());
        editor.apply();
    }

    private static void addId(String id){
        String idsString = getIdsString();
        idsString = idsString + "," + id;
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(KEY_IDS, idsString.replace(" ", ""));
        editor.apply();
    }

}
