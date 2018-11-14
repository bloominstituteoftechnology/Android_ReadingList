package com.thadocizn.readinglist.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.thadocizn.readinglist.R;
import com.thadocizn.readinglist.classes.Book;

public class EditBookActivity extends AppCompatActivity {

    EditText edBookTitle;
    EditText edReasonToRead;
    Boolean boolReadBook;
    String book;
    String csv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
         findViewById(R.id.editTextBookTitle);
         findViewById(R.id.editTextReasonToRead);

        book = getIntent().getStringExtra(MainActivity.BOOK_ID);
        csv = getIntent().getStringExtra(MainActivity.CSV_VERSION);

        if (csv != null){
            String strCurrentBook = csv;
            String[] parts = strCurrentBook.split(",");
            edBookTitle.setText(parts[1]);
            edReasonToRead.setText(parts[1]);
            boolReadBook = Boolean.valueOf(parts[2]);
            final String part = parts[1];
        }
    }
}
