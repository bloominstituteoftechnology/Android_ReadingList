package com.lambdaschool.android_readinglist;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class SharedPrefsDao {
    public static final String PREFS_ID_LIST = "id_list";
    public static final String PREFS_NEXT_ID = "next_id";
    public static final String PREFS_KEY_PREFIX = "book_";

    public static ArrayList<String> getAllBookIds() {
        String prefsIdList = MainActivity.preferences.getString(PREFS_ID_LIST, "");
        String[] prefsIdListArray = prefsIdList.split(",");
        ArrayList<String> prefsIdArrayList = new ArrayList<>(prefsIdListArray.length);
        if (!prefsIdList.equals("")) {
            prefsIdArrayList.addAll(Arrays.asList(prefsIdListArray));
        }
        return prefsIdArrayList;
    }

    public static void getAllBooks() {
        ArrayList<Book> prefsIdArrayList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String prefsIdList = MainActivity.preferences.getString(PREFS_KEY_PREFIX + i, "");

            if (!prefsIdList.equals("")) {
                Book bookToUnpack=new Book(prefsIdList);
                prefsIdArrayList.add(bookToUnpack);
            }
        }
        BookRepository.bookList=prefsIdArrayList;
    }

    public static String getNextId() {
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        int nextId = MainActivity.preferences.getInt(PREFS_NEXT_ID, 0);
        editor.putInt(PREFS_NEXT_ID, ++nextId);
        editor.apply();
        return (Integer.toString(nextId));
    }

    public static void updateBook(Book bookToUpdate) {
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(MainActivity.DEFAULT_SHARED_PREFERENCES_KEY, "Sample Value");

        int currentId = Integer.parseInt(bookToUpdate.getId());
        int nextId = Integer.parseInt(getNextId());
        if (currentId < nextId) {
            BookRepository.updateBookInList(bookToUpdate);
        } else {
            BookRepository.bookList.add(bookToUpdate);
        }

        getNextId();

        ArrayList<String> ids = getAllBookIds();

        StringBuilder newIdList = new StringBuilder();
        for (String id : ids) {
            newIdList.append(id).append(",");
        }

        editor.putString(PREFS_ID_LIST, newIdList.toString());
        editor.putString(PREFS_KEY_PREFIX + bookToUpdate.getId(), bookToUpdate.toCsvString());
        editor.apply();
    }

/*    public static void updateBook(Book bookToUpdate){
        int currentId = Integer.parseInt(bookToUpdate.getId());
        int nextId=Integer.parseInt(getNextId());
        if(currentId < nextId) {
            BookRepository.updateBookInList(bookToUpdate);
        } else {
            BookRepository.bookList.add(bookToUpdate);
        }
    }*/
}
