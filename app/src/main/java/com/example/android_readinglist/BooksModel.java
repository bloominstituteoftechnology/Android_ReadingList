package com.example.android_readinglist;

import android.support.annotation.NonNull;

import java.util.ArrayList;



    public class BooksModel {

        @NonNull
        public static ArrayList<Book> getBooks() {
            return SharedPrefsDao.getBooks();
        }

        public static Book getBook(String id) {
            return SharedPrefsDao.getBook(id);
        }

        public static String nextBookId() {
            return SharedPrefsDao.getNextId();
        }

        /**
         * Update or add a new book
         * @return true if the book was updated, otherwise it was added
         * */
        public static boolean updateBook(Book book) {
            return SharedPrefsDao.updateBook(book);
        }

    }





