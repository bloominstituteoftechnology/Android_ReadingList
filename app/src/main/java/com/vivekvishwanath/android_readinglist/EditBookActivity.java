package com.vivekvishwanath.android_readinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class EditBookActivity extends AppCompatActivity {
    Context context;
    EditText bookNameText;
    EditText bookReasonText;
    Switch readSwitch;
    Button saveButton;
    Button cancelButton;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        context = this;
        bookNameText = findViewById(R.id.book_name_text);
        bookReasonText = findViewById(R.id.book_reason_text);
        readSwitch = findViewById(R.id.read_switch);
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);
    }

    @Override
    protected void onResume() {
        super.onResume();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnData();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.NEW_BOOK_TAG);
        String bookCsv = intent.getStringExtra(Constants.EDIT_BOOK_TAG);
        if (bookCsv != null) {
            Book book = new Book(bookCsv);
            bookNameText.setText(book.getTitle());
            bookReasonText.setText(book.getReasonToRead());
            readSwitch.setChecked(book.isHasBeenRead());
            id = book.getId();
        }
    }

    private void returnData() {
        String bookName = bookNameText.getText().toString();
        String bookReason = bookReasonText.getText().toString();
        boolean switchStatus = readSwitch.isChecked();
        Book book = new Book(id, bookName, bookReason, readSwitch.isChecked());
        String bookCsv = book.toCsvString();
        Intent intent = new Intent();
        intent.putExtra(Constants.EDIT_BOOK_TAG, bookCsv);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void cancel() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        returnData();
    }
}
