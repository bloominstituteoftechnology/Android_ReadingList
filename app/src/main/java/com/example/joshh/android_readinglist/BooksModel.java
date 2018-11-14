package com.example.joshh.android_readinglist;

import java.util.ArrayList;

public class BooksModel {
    public static ArrayList<Book> getAllBooks(){
        ArrayList<Book> booksArray = SharedPrefsDao.getAllBooks();
        return booksArray;
    }

    public Book getSingleBook(String id){
        return SharedPrefsDao.getBook(id);
    }

    public String nextId(){
        return SharedPrefsDao.getNextId();
    }

    public static void updateBook(Book book){
        SharedPrefsDao.updateBook(book);
    }

}
