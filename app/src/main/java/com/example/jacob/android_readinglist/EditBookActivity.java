package com.example.jacob.android_readinglist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity {

    public static final String EDIT_BOOK_KEY = "edit_book";
    Book book;
    String bookAsCSVString;
    EditText editTextTitle, editTextReasonToRead;
    CheckBox chkHasBeenRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        editTextTitle = findViewById(R.id.edittext_title);
        editTextReasonToRead = findViewById(R.id.edittext_reason_to_read);
        chkHasBeenRead = findViewById(R.id.chkx_has_been_read);


        bookAsCSVString = getIntent().getStringExtra(EDIT_BOOK_KEY);

        if (bookAsCSVString != null) {
            book = new Book(bookAsCSVString);
            editTextTitle.setText(book.title);
            editTextReasonToRead.setText(book.reasonToRead);
            chkHasBeenRead.setChecked(book.hasBeenRead);
        } else {
            book = new Book(Book.NO_ID, "", "", false);
        }

        findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelData();
            }
        });

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnData();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        returnData();
    }

    private void returnData() {
        book.setTitle(editTextTitle.getText().toString());
        book.setReasonToRead(editTextReasonToRead.getText().toString());
        book.setHasBeenRead(chkHasBeenRead.isChecked());
        Intent intent = new Intent();
        intent.putExtra(EDIT_BOOK_KEY, book.toCSVString());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void cancelData() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }
}
