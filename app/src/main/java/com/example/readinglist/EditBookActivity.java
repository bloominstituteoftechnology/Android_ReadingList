package com.example.readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import static com.example.readinglist.MainActivity.ADD_REQUEST_CODE;
import static com.example.readinglist.MainActivity.EDIT_REQUEST_CODE;

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

        //Checks the request code the intent was sent with to either populate all values or just the ID (constants imported)
        int requestCode = intent.getIntExtra("RequestCode", 0);
        if (requestCode == ADD_REQUEST_CODE) {
            id = intent.getStringExtra("id");
        }
        else if (requestCode == EDIT_REQUEST_CODE) {
            bookCsv = intent.getStringExtra("BookCsv");
            Book book = new Book(bookCsv);
            name.setText(book.getTitle());
            reasonToRead.setText(book.getReasonToRead());
            checkBox.setChecked(book.hasBeenRead);
            id = book.getId();
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
