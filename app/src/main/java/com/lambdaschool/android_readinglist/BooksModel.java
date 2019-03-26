package com.lambdaschool.android_readinglist;

import java.util.ArrayList;

public class BooksModel {
    public static ArrayList<Book> getAllBooks() {
        return BookRepository.bookList;
    }

    public static Book getBookFromId(String id) {
        for (int i = 0; i < BookRepository.bookList.size(); i++) {
            Book bookInList = BookRepository.bookList.get(i);

            if (bookInList.getId().equals(id)) {
                return bookInList;
            }
        }
        return null;
    }

    public static String getNextId() {
        return SharedPrefsDao.getNextId();
    }

    public static void updateBook(Book bookToUpdate) {
        SharedPrefsDao.updateBook(bookToUpdate);
    }
}
