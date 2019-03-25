package com.example.israel.readinglist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SharedPrefsDAO {

    static public String UNADDED_BOOK_ID = "unadded_id";
    static public int UNINITIALIZED_SP_NEXT_ID = -1;
    static private String SP_KEY_NEXT_ID = "next_id";
    static private String SP_KEY_IDS = "ids";

    static public String getNextBookId() {
        int nextId = MainActivity.preferences.getInt(SP_KEY_NEXT_ID, -1);
        if (nextId == UNINITIALIZED_SP_NEXT_ID) {
            // initialize nextId
            nextId = 0;
        } else {
            ++nextId;
        }

        MainActivity.preferences.edit().putInt(SP_KEY_NEXT_ID, nextId).apply();

        return "id:" + Integer.toString(nextId);
    }

    @NonNull
    static public ArrayList<String> getBookIds() {
        Set<String> idSet = MainActivity.preferences.getStringSet(SP_KEY_IDS, null);
        if (idSet == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(idSet);
    }

    static private void addBookId(String id) {
        Set<String> idSet = MainActivity.preferences.getStringSet(SP_KEY_IDS, null);
        if (idSet == null) {
            // initialize ids
            idSet = new HashSet<>();
        }

        idSet.add(id);
        MainActivity.preferences.edit().putStringSet(SP_KEY_IDS, idSet).apply();
    }

    @NonNull
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

    static public Book getBook(String id) {
        String bookCSVString = getBookCSVString(id);
        if (bookCSVString == null) {
            return null;
        }

        return new Book(bookCSVString);
    }

    @Nullable
    static public String getBookCSVString(String id) {
        return MainActivity.preferences.getString(id, null);
    }

    /**
     * Update or add a new book
     * @return true if the book was updated, otherwise it was added
     * */
    static public boolean updateBook(Book book) {
        if (book.getId().equals(UNADDED_BOOK_ID)) { // book hasn't been added yet
            // add book csv in the SP
            String newId = getNextBookId();
            book.setId(newId);
            MainActivity.preferences.edit().putString(newId, book.toCSVString()).apply();
            addBookId(newId);
            return false; // added
        }

        // update the book csv in the SP
        MainActivity.preferences.edit().putString(book.getId(), book.toCSVString()).apply();

        return true; // updated
    }

}
