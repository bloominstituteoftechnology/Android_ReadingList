package com.vivekvishwanath.android_readinglist;

import android.Manifest;
import android.content.SharedPreferences;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SharedPrefsDao {
    private static final String ID_LIST_KEY = "id list";
    private static final String BOOK_ITEM_KEY_PREFIX = "book#";
    private static final String NEXT_ID_KEY = "next id";

    public static String getAllBookIds() {
        String ids = MainActivity.preferences.getString(ID_LIST_KEY, "");
        return ids;
    }

    public static String getNextId() {
        return MainActivity.preferences.getString(NEXT_ID_KEY, "0");
    }

    public static String getBookCsv(String id) {
        String bookCsv = MainActivity.preferences.getString(BOOK_ITEM_KEY_PREFIX + id, "");
        return bookCsv;
    }

    public static void updateBook(Book book) {
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        String idsString = getAllBookIds();
        String[] parsedIds = idsString.split(",");
        ArrayList<String> idList = new ArrayList<>(parsedIds.length);
        idList.addAll(Arrays.asList(parsedIds));
        // new entry
        if (!idList.contains(book.getId())) {
            int nextId = Integer.parseInt(MainActivity.preferences.getString(NEXT_ID_KEY, "0"));
            book.setId(String.valueOf(nextId));
            editor.putString(NEXT_ID_KEY, Integer.toString(++nextId));
            idList.add(book.getId());
            StringBuilder ids = new StringBuilder();
            for (String id : idList) {
                ids.append(id).append(",");
            }
            editor.putString(ID_LIST_KEY, ids.toString());
            editor.putString(BOOK_ITEM_KEY_PREFIX + book.getId(), book.toCsvString());
            editor.apply();
        } else {
            editor.putString(BOOK_ITEM_KEY_PREFIX + book.getId(), book.toCsvString());
            editor.apply();
        }
    }
}



