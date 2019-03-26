package com.example.readinglist;

import android.content.SharedPreferences;

import java.util.ArrayList;

import static com.example.readinglist.SharedPrefsDao.findBookById;
import static com.example.readinglist.SharedPrefsDao.getAllBookIds;

public class BooksModel {

    public ArrayList<Book> findAllBooks(){
        ArrayList<String> allBookIds = SharedPrefsDao.getAllBookIds();
        ArrayList<Book> allBooks = new ArrayList<>();
        for (String id: allBookIds) {
            allBooks.add (findBook(Integer.parseInt(id)));
        }

        return allBooks;

    }

    public static Book findBook(int id) {
        String csvString = SharedPrefsDao.findBookById(id);
        if(!csvString.equals("invalid")) {
            return new Book(csvString);
        } else {
            return null;
        }
    }
    public int findNextBookId() {
        return Integer.parseInt(SharedPrefsDao.getNextId());
    }

    public void saveBook(Book book){
        SharedPrefsDao.updateBook(book);
    }


}
