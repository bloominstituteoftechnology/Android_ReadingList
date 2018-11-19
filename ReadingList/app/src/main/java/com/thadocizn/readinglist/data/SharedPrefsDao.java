package com.thadocizn.readinglist.data;

import android.content.SharedPreferences;

import com.thadocizn.readinglist.activities.MainActivity;
import com.thadocizn.readinglist.classes.Book;
import com.thadocizn.readinglist.classes.Constants;

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

    private static String getNextId() {

            int currentId = MainActivity.preferences.getInt(Constants.NEXT_KEY_ID, 0);
            int nextId    = currentId + 1;
            String strNextId = String.valueOf(nextId);
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            editor.putString(Constants.NEXT_KEY_ID, strNextId);
            editor.apply();
        return String.valueOf(currentId);
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
        editor.putString(Constants.KEY_IDS, strGetId);
        editor.apply();
    }

    private static void addBook(Book book){
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(Constants.KEY_ID_PREFIX + book.getId(), book.toCsvString());
        editor.apply();
    }

}
