package com.example.israel.readinglist;

import android.support.annotation.NonNull;

import java.util.ArrayList;

public class BooksModel {

    @NonNull
    public static ArrayList<Book> getBooks() {
        return SharedPrefsDAO.getBooks();
    }

    public static Book getBook(String id) {
        return SharedPrefsDAO.getBook(id);
    }

    public static String nextBookId() {
        return SharedPrefsDAO.getNextBookId();
    }

    /**
     * Update or add a new book
     * @return true if the book was updated, otherwise it was added
     * */
    public static boolean updateBook(Book book) {
        return SharedPrefsDAO.updateBook(book);
    }

}
