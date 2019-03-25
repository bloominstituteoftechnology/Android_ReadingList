package com.example.readinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity {
    String id;
    String bookCsv;
    EditText name;
    EditText reasonToRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        Intent intent = getIntent();
        name = findViewById(R.id.view_text_name);
        reasonToRead = findViewById(R.id.view_text_reason_to_read);

        try {
            id = intent.getStringExtra("id");
        } catch(Exception e) {
            Log.i ("ID Setter", "Failed to set ID via button");
        }
        try {
            bookCsv = intent.getStringExtra("BookCsv");
            Book book = new Book(bookCsv);
            name.setText(book.getTitle());
            reasonToRead.setText(book.getReasonToRead());
            id = book.getId();

        } catch(Exception e ) {
            Log.i("ID Setter", "Failed to get Book object");
        }



    }
}
