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
        String currentId;
        String stringNextId = "";
        if (MainActivity.preferences != null) {
            currentId = MainActivity.preferences.getString(NEXT_KEY_ID, "");
            int intCurrentId = Integer.parseInt(currentId);
            int nextId = intCurrentId + 1;
            stringNextId = Integer.toString(nextId);
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            editor.putString(NEXT_KEY_ID, stringNextId);
            editor.apply();
        }
        return stringNextId;
    }

    public static void updateBook(Book book){
        if(book.getId() == ""){
            book.setId(getNextId());
        }
        String[] ids = getAllIds();
        boolean exists = false;
        for(String id : ids){
            if(!id.equals("")){
                if(book.getId() == (id)){
                    exists = true;
                    break;
                }
            }
        }
        if(!exists){
            addId(book.getId());
        }
        addBook(book);
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
