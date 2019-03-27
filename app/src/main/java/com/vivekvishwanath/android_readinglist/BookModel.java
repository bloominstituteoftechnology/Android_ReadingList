package com.vivekvishwanath.android_readinglist;

import java.util.ArrayList;

public class BookModel {

    public static Book[] getBookArray() {
        Book [] books = new Book[Integer.parseInt(SharedPrefsDao.getNextId())];
        for (int i = 0; i < books.length; i++) {
            String bookCsv = SharedPrefsDao.getBookCsv(Integer.toString(i));
            books[i] = new Book(bookCsv);
        }

        return books;
    }

    public static Book getBook(int id) {
        Book book = new Book(SharedPrefsDao.getBookCsv(Integer.toString(id)));
        return book;
    }

    public static int nextId () {
        return Integer.parseInt(SharedPrefsDao.getNextId());
    }

    public static void updateBook(Book book) {
        SharedPrefsDao.updateBook(book);
    }

}
