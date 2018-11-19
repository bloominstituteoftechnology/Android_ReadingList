package com.thadocizn.readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.readinglist.ViewModel.BookModel;
import com.thadocizn.readinglist.activities.EditBookActivity;
import com.thadocizn.readinglist.classes.Book;
import com.thadocizn.readinglist.classes.Constants;

import java.util.ArrayList;

public class BookController {
   static LinearLayout parentLayout;

    public static View getBooksView(Context context, Activity activity){
        ArrayList<Book> books;
        books = BookModel.getAllBooks();
        parentLayout = new LinearLayout(context);
        parentLayout.setOrientation(LinearLayout.VERTICAL);
        for(Book book : books){
            getTextView(book, activity, context, parentLayout );
        }
        return parentLayout;
    }
    private static TextView getTextView(final Book book, final Activity activity, final Context
                                 context, LinearLayout linearLayout) {
        TextView textView = new TextView(context);
        textView.setText(book.getTitle());
        textView.setTextSize(24);
        textView.setPadding(10, 10, 10, 10);
        parentLayout.addView(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(Constants.CSV_STRING, book.toCsvString());
                activity.startActivityForResult(intent, Constants.EDIT_BOOK_REQUESTCODE);
            }
        });
        return textView;
    }

    public static void handleEditActivityResult(Intent intent){
        String csvString  = intent.getStringExtra(Constants.EDIT_BOOK_KEY);
        String[] csv      = csvString.split(",");
        String title      = csv[0];
        String reason     = csv[1];
        String id         = csv[2];
        Boolean read      = Boolean.parseBoolean(csv[3]);
        Book returnedBook = new Book(title, reason, id, read);
        BookModel.updateBook(returnedBook);
    }

}
