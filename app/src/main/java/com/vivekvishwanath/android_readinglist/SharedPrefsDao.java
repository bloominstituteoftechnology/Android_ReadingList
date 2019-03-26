package com.vivekvishwanath.android_readinglist;

import java.util.ArrayList;
import java.util.Arrays;

public class SharedPrefsDao {
    private static final String ID_LIST_KEY = "id list";
    private static final String NEXT_ID_KEY = "next id";

    private ArrayList<String> getallBookIds() {
        String idList = MainActivity.preferences.getString(ID_LIST_KEY, "");
        String[] oldList = idList.split(",");
        ArrayList<String> ids = new ArrayList<>(oldList.length);
        if (!idList.equals("")) {
            ids.addAll(Arrays.asList(oldList));
        }
        return ids;
    }


}



