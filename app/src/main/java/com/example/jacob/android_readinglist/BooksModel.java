package com.example.jacob.android_readinglist;

import java.util.ArrayList;

public class BooksModel extends SharedPrefsDao {

    public static ArrayList<Book> getAllBooks() {
        ArrayList<Book> allBooks = new ArrayList<Book>();
        String[] allIds = getAllBookIds();
        if (allIds != null) {
            for (String id : allIds) {
                allBooks.add(getSingleBook(id));
            }
        }
        return allBooks;
    }

    public static Book getSingleBook(String id) {
        return getBook(id);
    }

    private static int NextId() {
        return getNextId();
    }

    public static void changeBook(Book book) {
        updateBook(book);
    }
}
