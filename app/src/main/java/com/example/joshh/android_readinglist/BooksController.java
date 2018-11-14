package com.example.joshh.android_readinglist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.GridLayout.LayoutParams;

import java.util.ArrayList;
import java.util.Random;

public class BooksController {

    private static LinearLayout linearLayout;
    private static Book book;

    public static View getBooksView(Context context){
        ArrayList<Book> books;
        books = BooksModel.getAllBooks();
        Log.i("allbooks", books.toString());
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for(Book book : books){
           buildItemView(book, context, linearLayout);
        }
        return linearLayout;
    }

    public static void handleEditActivityResult(Intent intent){
        String csvString = intent.getStringExtra(EditBookActivity.EDIT_BOOK_KEY);
        String[] stringArray = csvString.split(",");
        book = new Book(stringArray[0], stringArray[1], stringArray[2], Boolean.parseBoolean(stringArray[3]));
        BooksModel.updateBook(book);
    }

    private static View buildItemView(final Book book, final Context context, LinearLayout ll){
        TextView view = new TextView(context);
        //view.getLayoutParams().width=  ViewGroup.LayoutParams.MATCH_PARENT;
        view.setText(book.getTitle());
        ll.addView(view);
        view.setTextSize(40);
        view.setPadding(55, 0, 0, 8);
        view.setTextColor(Color.BLACK);
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editBookIntent = new Intent(context, EditBookActivity.class);
                editBookIntent.putExtra(EditBookActivity.EDIT_BOOK_KEY, book.toCsvString());

            }
        });*/
        return view;
    }

}
