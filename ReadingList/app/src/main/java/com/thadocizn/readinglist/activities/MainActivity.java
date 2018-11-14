package com.thadocizn.readinglist.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.readinglist.R;
import com.thadocizn.readinglist.classes.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BOOK_ID = "book_id";
    public static final String CSV_VERSION = "csv_version";
    LinearLayout parentLayout;
    Context context;
    ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentLayout = findViewById(R.id.parentLayout);
        context = this;
        books = new ArrayList<Book>();

        books.add(new Book("Cat in the Hat", "Good book", true, "1"));
        books.add(new Book("Bible", "Good book", true, "1"));
        books.add(new Book("Coding for Dummies", "Good book", true, "1"));



        findViewById(R.id.btnAddBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = parentLayout.getChildCount() -1;

                for (Book book : books) {
                    parentLayout.addView(getTextView(book));

                }

                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(BOOK_ID, Integer.toString(index));
                startActivity(intent);
            }
        });
        
    }

    private TextView getTextView(final Book book) {
        TextView textView = new TextView(context);
        textView.setText(book.getTitle());
        textView.setTextSize(24);
        textView.setPadding(10, 10, 10, 10);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(CSV_VERSION, book.toCsvString());
                startActivity(intent);
            }
        });
        return textView;
    }

}
