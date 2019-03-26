package com.example.readinglist;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.readinglist.MainActivity.MY_PREFERENCES;
import static com.example.readinglist.MainActivity.context;

public class SharedPrefsDao {
    static private String ID_LIST_KEY = "id_list";
    static private String NEXT_ID_KEY = "next_id";
    private static final String ENTRY_ITEM_KEY_PREFIX = "entry_";
    static private SharedPreferences preferences = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);



    public static ArrayList<String> getAllBookIds() { //DONE
        //Get currently stored string
        String idList = preferences.getString(ID_LIST_KEY, "");
        //split string into array of strings
        String[] oldList = idList.split(",");
        //initialize arraylist
        ArrayList<String> ids = new ArrayList<>();
        //convert array of strings into arraylist
        if(!idList.equals("")){
            ids.addAll(Arrays.asList(oldList));
        }
        return ids;
    }

    public static String getNextId() { //DONE
        String nextId = preferences.getString(NEXT_ID_KEY, "0");
        return nextId;
    }

    public static String findBookById(int id) { //Should Return Book CsvString
        return preferences.getString(ENTRY_ITEM_KEY_PREFIX + id, "invalid");
        /*if(!entryCsv.equals("invalid")) {
            Book book = new Book(entryCsv);
            return book;
        } else {
            return null;
        }*/

    }

    public static void updateBook(Book book) {  //DONE

        SharedPreferences.Editor editor = preferences.edit();
        if(Integer.parseInt(book.getId()) >= BooksModel.findNextBookId()) {

            //find old next id, in order to update it
            String nextId = preferences.getString(NEXT_ID_KEY, "0");
            //increment and store nextId String
            int nextIdInt = Integer.parseInt(nextId);
            nextIdInt++;
            nextId = Integer.toString(nextIdInt);
            editor.putString(NEXT_ID_KEY, (nextId));
            //read list of entry ids
            ArrayList<String> ids = getAllBookIds();
            //add current id to working memory
            ids.add(book.getId());

            //store updated id list
            StringBuilder newIdList = new StringBuilder();
            for (String id : ids) {
                newIdList.append(id).append(",");
            }
            editor.putString(ID_LIST_KEY, newIdList.toString());
        }

            //store entry (new or edited, it doesn't matter which)
            editor.putString((ENTRY_ITEM_KEY_PREFIX + book.getId()), Book.toCsvString(book));
            editor.apply();

//
//
//
//
//        }

    }
}


