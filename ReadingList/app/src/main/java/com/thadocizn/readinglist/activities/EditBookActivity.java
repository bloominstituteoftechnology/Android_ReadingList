package com.thadocizn.readinglist.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.thadocizn.readinglist.R;
import com.thadocizn.readinglist.classes.Book;
import com.thadocizn.readinglist.classes.Constants;

public class EditBookActivity extends AppCompatActivity {
    EditText edBook;
    EditText edReasonToRead;
    Book book;
    String strId = "";
    String strBook;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        preferences = this.getSharedPreferences(Constants.COM_THADOCIZN_READING_LIST, Context.MODE_PRIVATE);

        strId   = getIntent().getStringExtra(Constants.KEY_ID);
        strBook = getIntent().getStringExtra(Constants.CSV_STRING);

        edBook         = findViewById(R.id.edBookTitle);
        edReasonToRead = findViewById(R.id.edReasonToRead);

        getBook();


        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnData();
            }
        });

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelData();
            }
        });

    }

    private void getBook() {
        if (strBook != null){
            book = new Book(strBook);
            edBook.setText(book.getTitle());
            edReasonToRead.setText(book.getReasonToRead());

        }
        book = new Book(strId);
        book.setTitle(edBook.getText().toString());
        book.setReasonToRead(edReasonToRead.getText().toString());
        book.setHasBeenRead(true);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        returnData();
    }

    private void returnData() {
        String strTitle = edBook.getText().toString();
        String strReasonToRead = edReasonToRead.getText().toString();
        String strBookId = book.getId();
        Boolean boolHaveRead = book.isHasBeenRead();

        String strBook = new Book(strTitle, strReasonToRead, strBookId, boolHaveRead).toCsvString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra(Constants.STRING_BOOK, strBook);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

    private void cancelData(){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

}
