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
    Book currentbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

         edBookTitle = findViewById(R.id.editTextBookTitle);
         edReasonToRead = findViewById(R.id.editTextReasonToRead);

        book = getIntent().getStringExtra(MainActivity.BOOK_ID);
        csv = getIntent().getStringExtra(MainActivity.CSV_VERSION);

        setEditViews(csv);
    }

    private void setEditViews(String csv) {
        if (csv != null){
            currentbook = new Book(csv);

            String strCurrentBook = currentbook.toCsvString();
            String[] parts = strCurrentBook.split(",");
            String strBookTitle = parts[0];
            String strReasonToRead = parts[1];
            String strReadBook = parts[2];
            
            edBookTitle.setText(strBookTitle);
            edReasonToRead.setText(strReasonToRead);
            boolReadBook = Boolean.valueOf(strReadBook);
        }
    }
}
