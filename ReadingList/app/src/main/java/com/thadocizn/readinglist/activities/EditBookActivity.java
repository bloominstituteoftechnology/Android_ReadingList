package com.thadocizn.readinglist.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thadocizn.readinglist.R;
import com.thadocizn.readinglist.classes.Book;

public class EditBookActivity extends AppCompatActivity {

    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        //book = getIntent().getIntExtra(MainActivity.BOOK_ID, 0);

    }
}
