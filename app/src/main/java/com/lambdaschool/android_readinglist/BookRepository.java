package com.lambdaschool.android_readinglist;

import java.util.ArrayList;

public class BookRepository {
    public static ArrayList<Book> bookList = new ArrayList<>();

    public static void addBookToList(Book bookToAdd) {
        removeBookFromList(bookToAdd);
        bookList.add(bookToAdd);

    }

    public static void removeBookFromList(Book bookToRemove) {
        if (bookToRemove != null) {

            for (int i = 0; i < bookList.size(); i++) {
                Book bookInList = bookList.get(i);

                if (bookInList.getId().equals(bookToRemove.getId())) {
                    bookList.remove(i);
                    break;
                }
            }
        }
    }

    public static String generateId() {
        int newId = 0;

        for (int i = 0; i < bookList.size(); i++) {
            Book bookInList = bookList.get(i);
            int candidateId = Integer.parseInt(bookInList.getId());

            if (candidateId >= newId) {
                newId = candidateId + 1;
            }
        }

        return Integer.toString(newId);
    }
}
