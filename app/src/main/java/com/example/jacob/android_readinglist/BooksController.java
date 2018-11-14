package com.example.jacob.android_readinglist;

import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;

import java.util.ArrayList;

public class BooksController extends BooksModel {

    private static TextView buildItemView(final Book book, final Activity activity) {
        TextView textView = new TextView(activity);
        textView.setText(book.getTitle());
        if (book.getHasBeenRead()) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        textView.setTextSize(24);
        textView.setPadding(10, 10, 10, 10);
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, EditBookActivity.class);
                intent.putExtra(EditBookActivity.EDIT_BOOK_KEY, book.toCSVString());
                activity.startActivityForResult(intent,Constants.EDIT_REQUEST_CODE);
            }
        });

        return textView;
    }

    public static LinearLayout getBooksView(Activity activity) {
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView returnedTextView;
        ArrayList<Book> allBooks;
        allBooks = getAllBooks();
        for (Book book : allBooks) {
            returnedTextView = buildItemView(book, activity);
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
