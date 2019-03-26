package com.vivekvishwanath.android_readinglist;

import java.util.ArrayList;

public class BookRepository {
    private static ArrayList<Book> bookList = new ArrayList<>();

    public static void addBook(Book book) {
        bookList.add(book);
    }

    public static Book getBook(int index) {
        return bookList.get(index);
    }

    public static void replaceBook(int index, Book book) {
        bookList.set(index, book);
    }

    public static ArrayList<Book> getBookList() {
        return bookList;
    }
}
