package com.jakeesveld.readinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

public class EditActivity extends AppCompatActivity {
    EditText editBookTitle;
    EditText editBookReason;
    Switch switchHasRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editBookTitle = findViewById(R.id.edit_book_title);
        editBookReason = findViewById(R.id.edit_book_reason);
        switchHasRead = findViewById(R.id.switch_has_read);

        Intent intent = getIntent();
        if(intent.hasExtra("id")){
            int newBookId = intent.getIntExtra("id", -1);
        }

        if(intent.hasExtra("book")) {
            String passedBookCSV = intent.getStringExtra("book");
                Book editableBook = new Book(passedBookCSV);
                editBookTitle.setText(editableBook.getTitle());
                editBookReason.setText(editableBook.getReasonToRead());
                switchHasRead.setChecked(editableBook.getHasBeenRead());
        }
    }
}
