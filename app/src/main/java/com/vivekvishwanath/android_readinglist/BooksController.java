package com.vivekvishwanath.android_readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class BooksController {

    public static TextView buildItemView(final Book book, final Context context) {
        TextView bookView = new TextView(context);
        bookView.setText(book.getTitle());
        bookView.setTextSize(18);
        bookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(Constants.EDIT_BOOK_TAG, book.toCsvString());
                ((Activity)context).startActivityForResult(intent, Constants.EDIT_BOOK_REQUEST_CODE);
            }
        });
        return bookView;
    }
}
