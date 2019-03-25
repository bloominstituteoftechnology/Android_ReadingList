package com.vivekvishwanath.android_readinglist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Context context;
    Button addBookButton;
    LinearLayout bookViewLayout;
    public static final int BOOK_REQUEST_CODE = 2;

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
                intent.putExtra(Book.NEW_BOOK_TAG, bookViewLayout.getChildCount());
                startActivityForResult(intent, BOOK_REQUEST_CODE);
            }
        });
        bookViewLayout = findViewById(R.id.book_view_layout);

    }

    private TextView buildItemView(final Book book) {
        TextView bookView = new TextView(context);
        bookView.setText(book.getTitle());
        bookView.setTextSize(18);
        bookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Book.EDIT_BOOK_TAG, book.toCsvString());
                startActivityForResult(intent, BOOK_REQUEST_CODE);
            }
        });
        return bookView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == BOOK_REQUEST_CODE && requestCode == RESULT_OK) {
            String bookCsv = data.getStringExtra(Book.EDIT_BOOK_TAG);
        }
    }
}
