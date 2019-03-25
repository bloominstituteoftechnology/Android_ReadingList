package com.example.israel.readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BooksController {

    static private void buildItemView(final LinearLayout linearLayout, final Book book) {
        TextView newTextView = new TextView(linearLayout.getContext());
        newTextView.setText(book.getTitle());
        newTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.f);
        newTextView.setTypeface(Typeface.DEFAULT_BOLD);

        newTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(linearLayout.getContext(), EditBookActivity.class);
                intent.putExtra(MainActivity.KEY_BOOK_CSV_STRING, book.toCSVString());
                ((Activity)linearLayout.getContext()).startActivityForResult(intent, MainActivity.REQUEST_EDIT_BOOK);
            }
        });

        linearLayout.addView(newTextView);

    }

    static public LinearLayout getBooksView(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams
                (new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        ArrayList<Book> books = BooksModel.getBooks();
        for (Book book : books) {
            buildItemView(linearLayout, book);
        }
        return linearLayout;
    }

    static public void handleEditActivityResult(Intent intent) {
        if (intent != null) {
            String bookCSVString = intent.getStringExtra(MainActivity.KEY_BOOK_CSV_STRING);
            BooksModel.updateBook(new Book(bookCSVString));
        }
    }

}
