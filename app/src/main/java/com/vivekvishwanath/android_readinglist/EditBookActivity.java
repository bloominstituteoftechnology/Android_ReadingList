package com.vivekvishwanath.android_readinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

public class EditBookActivity extends AppCompatActivity {
    Context context;
    EditText bookNameText;
    EditText bookReasonText;
    Switch readSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        context = this;
        bookNameText = findViewById(R.id.book_name_text);
        bookReasonText = findViewById(R.id.book_reason_text);
        readSwitch = findViewById(R.id.read_switch);

        Intent intent = getIntent();
        String id = intent.getStringExtra(Book.NEW_BOOK_TAG);
        String bookCsv = intent.getStringExtra(Book.EDIT_BOOK_TAG);
        if (bookCsv != null) {
            Book book = new Book(bookCsv);
            bookNameText.setText(book.getTitle());
            bookReasonText.setText(book.getReasonToRead());
            readSwitch.setChecked(book.isHasBeenRead());
        }
    }
}
