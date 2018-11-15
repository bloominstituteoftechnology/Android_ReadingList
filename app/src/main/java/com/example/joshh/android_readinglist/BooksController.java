package com.example.joshh.android_readinglist;

import android.app.Activity;
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

    public static View getBooksView(Context context, Activity activity){
        ArrayList<Book> books;
        books = BooksModel.getAllBooks();
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for(Book book : books){
           buildItemView(book, context, linearLayout, activity);
        }
        return linearLayout;
    }

    public static void handleEditActivityResult(Intent intent){
        String csvString = intent.getStringExtra(EditBookActivity.EDIT_BOOK_KEY);
        String[] csv = csvString.split(",");
        String title = csv[0];
        String reason = csv[1];
        String id = csv[2];
        Boolean read = Boolean.parseBoolean(csv[3]);
        Book returnedBook = new Book(title, reason, id, read);
        BooksModel.updateBook(returnedBook);
    }

    private static View buildItemView(final Book book, final Context context, LinearLayout ll, final Activity activity){
        TextView view = new TextView(context);
        view.setText(book.getTitle());
        ll.addView(view);
        view.setTextSize(40);
        view.setPadding(55, 0, 0, 8);
        view.setTextColor(Color.BLACK);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra("csv_string", book.toCsvString());
                activity.startActivityForResult(intent, MainActivity.EDIT_BOOK_REQUESTCODE);
            }
        });
        return view;
    }

}
