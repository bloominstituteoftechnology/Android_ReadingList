package com.example.android_readinglist;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SharedPrefsDao {

public static final String ID_LIST_KEY = "BoogaBooga";
public static final String NEXT_ID__KEY = "next_id";
public static final String NEW_BOOK_ID = "this one is new";


    static public ArrayList<String> getBookIds() {
        Set<String> idSet = MainActivity.preferences.getStringSet(ID_LIST_KEY, null);
        if (idSet == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(idSet);}

    static public String getNextId() {
        int nextId = MainActivity.preferences.getInt(NEXT_ID__KEY, -1);
        if (nextId == -1) {
            nextId = 0;
        } else {
            ++nextId;
        }
        MainActivity.preferences.edit().putInt(NEXT_ID__KEY, nextId).apply();
        return "id = " + Integer.toString(nextId);
    }
    @Nullable
    static public String getBookString(String id){
        return MainActivity.preferences.getString("id", null);

    }
    static public Book getBook(String id){
        String bookString = getBookString(id);
        if(bookString == null){
            return null;
        }
        return new Book(bookString);
    }

    static public boolean updateBook(Book book){
        if(book.getId().equals(NEW_BOOK_ID)){
            String newId = getNextId();
            book.setId(newId);
            MainActivity.preferences.edit().putString(newId, book.toCsvString()).apply();
            addBookId(newId);
            return false;

        }

        MainActivity.preferences.edit().putString(book.getId(),book.toCsvString()).apply();
        return true;
    }

    static private void addBookId(String id) {
        Set<String> idSet = MainActivity.preferences.getStringSet(ID_LIST_KEY, null);
        if (idSet == null) {

            idSet = new HashSet<>();
        }

        idSet.add(id);
        MainActivity.preferences.edit().putStringSet(ID_LIST_KEY, idSet).apply();
    }

    static public ArrayList<Book> getBooks() {
        ArrayList<String> ids = getBookIds();
        ArrayList<Book> books = new ArrayList<>(ids.size());
        for (String id : ids) {
            String bookCSVString = MainActivity.preferences.getString(id, null);
            if (bookCSVString != null) {
                books.add(new Book(bookCSVString));
            }
        }

        return books;
    }


}

