package com.example.jacob.android_readinglist;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;

import java.util.ArrayList;

public class BooksController extends BooksModel {

    private static TextView buildItemView(final Book book, final Context context) {
        TextView textView = new TextView(context);
        textView.setText(book.getTitle());
        textView.setTextSize(24);
        textView.setPadding(10, 10, 10, 10);
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditBookActivity.class);
                String temp = book.toCSVString();
                intent.putExtra(EditBookActivity.EDIT_BOOK_KEY, book.toCSVString());
                context.startActivity(intent);
//                handleEditActivityResult(intent);
            }
        });

        return textView;
    }

    public static LinearLayout getBooksView(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView returnedTextView;
        ArrayList<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();
        for (Book book : allBooks) {
            returnedTextView = buildItemView(book, context);
            linearLayout.addView(returnedTextView);
        }

        return linearLayout;
    }

    protected static void handleEditActivityResult(Intent intent) {
        String dataString = intent.getStringExtra(Constants.UPDATE_REQUEST_CODE);
        Book returnedBook = new Book(dataString);
        BooksModel.changeBook(returnedBook);

    }


}
