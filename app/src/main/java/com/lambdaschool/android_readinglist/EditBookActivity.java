package com.lambdaschool.android_readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Date;

public class EditBookActivity extends AppCompatActivity {

    Context context;
    TextView textViewId;
    EditText editTextTitle;
    EditText editTextReason;
    Switch switchRead;
    String passedBookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        context = this;
        textViewId = (TextView) findViewById(R.id.text_view_id);
        editTextTitle = (EditText) findViewById(R.id.edit_text_title);
        editTextReason = (EditText) findViewById(R.id.edit_text_reason);
        switchRead = (Switch) findViewById(R.id.switch_read);

        Intent intent = getIntent();
        passedBookId = intent.getStringExtra(MainActivity.EXTRA_BOOK_NEW_TAG);
        String passedBookCsv = intent.getStringExtra(BooksController.EXTRA_BOOK_EDIT_TAG);

        if (passedBookCsv != null) {
            String[] components = passedBookCsv.split(",");
            Book bookToEdit = new Book(components[0], components[1], components[2], Boolean.parseBoolean(components[3]));

            textViewId.setText(bookToEdit.getId());
            editTextTitle.setText(bookToEdit.getTitle());
            editTextReason.setText(bookToEdit.getReasonToRead());
            switchRead.setChecked(bookToEdit.hasBeenRead());
        }

        Button buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTitle.getText().toString().trim().length() > 0) {
                    returnData();
                    Book bookNew = BooksModel.getBookFromId(textViewId.getText().toString());
                    if (bookNew == null) {
                        bookNew = new Book(textViewId.getText().toString(), editTextTitle.getText().toString(), editTextReason.getText().toString(), switchRead.isChecked());
                    }
                    BooksModel.updateBook(bookNew);
                } else
                    returnNoData();
            }
        });

        Button buttonCancel = findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnNoData();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (editTextTitle.getText().toString().trim().length() > 0) {
            returnData();
            Book bookNew = BooksModel.getBookFromId(textViewId.getText().toString());
            if (bookNew == null) {
                bookNew = new Book(textViewId.getText().toString(), editTextTitle.getText().toString(), editTextReason.getText().toString(), switchRead.isChecked());
            }
            BooksModel.updateBook(bookNew);
        } else
            returnNoData();
    }

    private void returnData() {
        String bookId = textViewId.getText().toString();
        if (bookId.equals("") && (passedBookId != null)) {
            bookId = passedBookId;
            textViewId.setText(bookId);
        }
        String bookTitle = editTextTitle.getText().toString();
        String bookReason = editTextReason.getText().toString();
        boolean bookRead = (switchRead.isChecked());
        Book bookToConstruct = new Book(bookId, bookTitle, bookReason, bookRead);
        Intent intent = new Intent();
        intent.putExtra(BooksController.EXTRA_BOOK_EDIT_TAG, bookToConstruct.toCsvString());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void returnNoData() {
        Book bookToConstruct = new Book();
        Intent intent = new Intent();
        intent.putExtra(BooksController.EXTRA_BOOK_EDIT_TAG, bookToConstruct.toCsvString());
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
