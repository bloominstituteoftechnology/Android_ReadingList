package com.example.jacob.android_readinglist;

import android.content.SharedPreferences;

public class SharedPrefsDao {
    private static final String KEY_IDS = "key_ids";
    private static final String KEY_ID_PREFIX = "key_id_";
    private static final String NEXT_KEY_ID = "key_next_id";

    public static String[] getAllBookIds() {
        String[] ids = null;
        String allIds = getAllIdsAsString();
        if (allIds != null) {
            ids = allIds.split(",");
        }
        return ids;
    }

    private static String getAllIdsAsString() {
        String keyIds = null;
        if (MainActivity.preferences != null) {
            keyIds = MainActivity.preferences.getString(KEY_IDS, null);
        }
        return keyIds;
    }

    public static int getNextId() {
        int currentId = 0;
        if (MainActivity.preferences != null) {
            currentId = MainActivity.preferences.getInt(NEXT_KEY_ID, 0);
            int nextId = currentId + 1;
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            editor.putInt(NEXT_KEY_ID, nextId);
            editor.apply();
        }
        return currentId;
    }

    public static Book getBook(String id) {
        Book book = null;
        if (MainActivity.preferences != null) {
            final String bookString = MainActivity.preferences.getString(KEY_ID_PREFIX + id, "");
            book = new Book(bookString);
        }
        return book;
    }

    public static void updateBook(Book updatedbook) {
        if (updatedbook.getId() == Book.NO_ID) {
            updatedbook.setId(getNextId());
        }
        String[] allIds = getAllBookIds();
        boolean alreadyExists = false;
        if (allIds != null) {
                        for (String id : allIds) {
                if (!id.equals("")) {
                    if (updatedbook.getId() == Integer.parseInt(id)) {
                        alreadyExists = true;
                        break;
                    }
                }
            }
        }

        if (!alreadyExists) {
            String idsString = getAllIdsAsString();
            if (idsString != null) {
                idsString = idsString + "," + updatedbook.getId();
            } else {
                idsString = Integer.toString(updatedbook.getId());
            }
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            editor.putString(KEY_IDS, idsString);
            editor.apply();
        }
        addBook(updatedbook);
    }

    private static void addBook(Book book) {
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(KEY_ID_PREFIX + book.getId(), book.toCSVString());
        editor.apply();
    }
}
