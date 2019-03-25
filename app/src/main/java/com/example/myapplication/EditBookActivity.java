package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

public class EditBookActivity extends AppCompatActivity {

    EditText tvReasonsToRead;
    EditText tvTitle;
    CheckBox hasBeenRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        Intent intent = getIntent();
        String eb = intent.getStringExtra("editBook");
        if(eb == "editBook"){
            if(intent.getStringExtra(Book.TAG) != null) {
                Book book = new Book(intent.getStringExtra(Book.TAG));
                populateFields(book);
            }

        }
        String id = intent.getStringExtra(Book.TAG);

    }

    public void populateFields(Book book){
        tvTitle.setText(book.getTitle());
        tvReasonsToRead.setText(book.getReasonToRead());
        this.hasBeenRead.setChecked(book.isHasBeenRead());
    }

}
