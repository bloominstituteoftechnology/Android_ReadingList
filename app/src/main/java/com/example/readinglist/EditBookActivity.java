package com.example.readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity {
    String id;
    String bookCsv;
    EditText name;
    EditText reasonToRead;
    CheckBox checkBox;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        context = this;
        Intent intent = getIntent();
        name = findViewById(R.id.view_text_name);
        reasonToRead = findViewById(R.id.view_text_reason_to_read);
        checkBox = findViewById(R.id.checkbox_already_read);

        //TODO: change this chunk to check the codes the intent was sent with
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

    public void returnData() {

        Book book = new Book(id,
                name.getText().toString(),
                reasonToRead.getText().toString(),
                checkBox.isChecked());
        String temp = Book.toCsvString(book);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("BOOK_KEY", temp);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
        returnData();

    }
}
