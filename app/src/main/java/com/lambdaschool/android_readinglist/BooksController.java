package com.lambdaschool.android_readinglist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BooksController {

    public static final String BOOK_DISPLAY_SEPARATOR = ": ";
    public static final String EXTRA_BOOK_EDIT_TAG = "edit-book";
    public static final int PADDING_FOR_TEXTVIEW = 15;
    public static final int TEXTSIZE_FOR_TEXTVIEW = 22;

    public static LinearLayout getBooksView(Context context) {
        ArrayList<Book> bookAL = BooksModel.getAllBooks();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

        for (Book bookSingle : bookAL) {
            linearLayout.addView(buildItemView(context, bookSingle));
        }

        return linearLayout;
    }

    public static TextView buildItemView(final Context context, final Book book) {
        TextView textview = new TextView(context);
        textview.setText(book.getId() + BOOK_DISPLAY_SEPARATOR + book.getTitle());
        textview.setPadding(PADDING_FOR_TEXTVIEW, PADDING_FOR_TEXTVIEW, PADDING_FOR_TEXTVIEW, PADDING_FOR_TEXTVIEW);
        textview.setTextSize(TEXTSIZE_FOR_TEXTVIEW);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(EXTRA_BOOK_EDIT_TAG, book.toCsvString());
                handleEditActivityResult(intent);
                //android.support.v4.app.FragmentActivity.startActivityForResult(intent, 0);
            }
        });
        return textview;
    }

    public static void handleEditActivityResult(Intent intent) {
        String bookInCsv = intent.getStringExtra(EXTRA_BOOK_EDIT_TAG);
        Book bookToAdd = new Book(bookInCsv);
        BookRepository.addBookToList(bookToAdd);

    }
}
