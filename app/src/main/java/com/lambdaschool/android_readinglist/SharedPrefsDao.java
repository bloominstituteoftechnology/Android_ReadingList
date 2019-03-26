package com.lambdaschool.android_readinglist;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class SharedPrefsDao {
    public static final String PREFS_ID_LIST = "id_list";
    public static final String PREFS_NEXT_ID = "next_id";

    public static ArrayList<String> getAllBookIds() {
        String prefsIdList = MainActivity.preferences.getString(PREFS_ID_LIST, "");
        String[] prefsIdListArray = prefsIdList.split(",");
        ArrayList<String> prefsIdArrayList = new ArrayList<>(prefsIdListArray.length);
        if (!prefsIdList.equals("")) {
            prefsIdArrayList.addAll(Arrays.asList(prefsIdListArray));
        }
        return prefsIdArrayList;
    }

    public static String getNextId() {
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        int nextId = MainActivity.preferences.getInt(PREFS_NEXT_ID, 0);
        //editor.putInt(PREFS_NEXT_ID, ++nextId);
        nextId += 1;
        return (Integer.toString(nextId));
    }

    public static void updateBook(Book bookToUpdate){
        int currentId = Integer.parseInt(bookToUpdate.getId());
        int nextId=Integer.parseInt(getNextId());
        if(currentId < nextId) {
            BookRepository.updateBookInList(bookToUpdate);
        } else {
            BookRepository.bookList.add(bookToUpdate);
        }
    }
}
