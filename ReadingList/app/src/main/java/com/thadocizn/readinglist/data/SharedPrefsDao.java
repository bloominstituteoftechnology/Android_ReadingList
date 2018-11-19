package com.thadocizn.readinglist.data;

import android.content.SharedPreferences;
import android.util.Log;

import com.thadocizn.readinglist.activities.MainActivity;
import com.thadocizn.readinglist.classes.Book;
import com.thadocizn.readinglist.classes.Constants;

import java.util.ArrayList;
import java.util.Arrays;

public class SharedPrefsDao {

    private static String getIds(){
        String keyIds = "";
        if (MainActivity.preferences != null){
            keyIds = MainActivity.preferences.getString(Constants.KEY_IDS, "");
        }
        return keyIds;
    }
    public static String[] getAllBookIds(){
        // keys are stored as csv
        return getIds().split(",");
    }

    public static Book getBook(String id){
        Book currentBook = null;
        if (MainActivity.preferences != null){
          final  String strBook = MainActivity.preferences.getString(Constants.KEY_ID_PREFIX + id, "");
          currentBook = new Book(strBook);
        }
        return currentBook;
    }

    public static ArrayList<Book> getAllBooks(){
        String[] ids = getAllBookIds();
        ArrayList<Book> books = new ArrayList<>(ids.length);
        for(String id : ids){
            if(!id.equals(""))
                books.add(getBook(id));
        }
        return books;
    }

    public static String getNextId() {

            String currentId = MainActivity.preferences.getString(Constants.NEXT_KEY_ID, "");
            int id = Integer.parseInt(currentId);
            int nextId    = id + 1;
            String strNextId = String.valueOf(nextId);
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            editor.putString(Constants.NEXT_KEY_ID, strNextId);
            editor.apply();
        return strNextId;
    }

    public static Book getBookCsv(String id) {
        Book book = null;
        if (MainActivity.preferences != null) {
            final String bookString = MainActivity.preferences.getString(Constants.KEY_ID_PREFIX + id, "");
            book = new Book(bookString);
        }
        return book;
    }

    public static void updateBook(Book book){
        if (book.getId().isEmpty()){
            book.setId(getNextId());
        }
        String[] ids = getAllBookIds();
        boolean active = false;
        for(String id : ids){
                if(book.getId().equals(id)){
                    active = true;
                    break;
                }
            }
        if(!active){
            addId(book.getId());
        }
        addBook(book);
    }

    public static void addId(String id){
        String strGetId = getIds();
        strGetId = strGetId + "," + id;

        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(Constants.KEY_IDS, strGetId.replace(" ", ""));
        editor.apply();
    }

    private static void addBook(Book book){
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(Constants.KEY_ID_PREFIX + book.getId(), book.toCsvString());
        editor.apply();
    }

}
