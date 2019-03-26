package com.vivekvishwanath.android_readinglist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    Button addBookButton;
    LinearLayout bookViewLayout;
    public static final int NEW_BOOK_REQUEST_CODE = 2;
    public static final int EDIT_BOOK_REQUEST_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        addBookButton = findViewById(R.id.add_book_button);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra("Book", String.valueOf(bookViewLayout.getChildCount()));
                startActivityForResult(intent, NEW_BOOK_REQUEST_CODE);
            }
        });
        bookViewLayout = findViewById(R.id.book_view_layout);

    }

    @Override
    protected void onResume() {
        super.onResume();
        bookViewLayout.removeAllViews();
        for (int i = 0; i < BookRepository.getBookList().size(); i++) {
            TextView bookView = buildItemView(BookRepository.getBook(i));
            bookViewLayout.addView(bookView);
        }
    }

    private TextView buildItemView(final Book book) {
        TextView bookView = new TextView(context);
        bookView.setText(book.getTitle());
        bookView.setTextSize(18);
        bookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(Book.EDIT_BOOK_TAG, book.toCsvString());
                startActivityForResult(intent, EDIT_BOOK_REQUEST_CODE);
            }
        });
        return bookView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == NEW_BOOK_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String bookCsv = data.getStringExtra(Book.EDIT_BOOK_TAG);
                Book book = new Book(bookCsv);
                BookRepository.addBook(book);
                ArrayList<Book> books = BookRepository.getBookList();
                int i = 0;
            }
        }
        if (requestCode == EDIT_BOOK_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String bookCsv = data.getStringExtra(Book.EDIT_BOOK_TAG);
                Book book = new Book(bookCsv);
                if (Integer.parseInt(book.getId()) < BookRepository.getBookList().size()) {
                    BookRepository.replaceBook(Integer.parseInt(book.getId()), book);

                }
            }
        }
    }
}
