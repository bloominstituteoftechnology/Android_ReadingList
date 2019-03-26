package com.lambdaschool.android_readinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

public class EditBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        Intent intent = getIntent();
        String passedBookId = intent.getStringExtra(MainActivity.EXTRA_BOOK_NEW_TAG);
        String passedBookCsv = intent.getStringExtra(MainActivity.EXTRA_BOOK_EDIT_TAG);

        if (passedBookCsv != null) {
            String[] components = passedBookCsv.split(",");
            Book bookToEdit= new Book(components[0],components[1],components[2],Boolean.parseBoolean(components[3]));

            ((EditText)findViewById(R.id.edit_text_title)).setText(bookToEdit.getTitle());
            ((EditText)findViewById(R.id.edit_text_reason)).setText(bookToEdit.getReasonToRead());
            ((Switch)findViewById(R.id.switch_read)).setChecked(bookToEdit.hasBeenRead());
        }
    }
}
