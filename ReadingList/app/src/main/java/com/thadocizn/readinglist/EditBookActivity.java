package com.thadocizn.readinglist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity {
    EditText edBook;
    EditText edReasonToRead;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        String strId   = getIntent().getStringExtra(Constants.KEY_ID);
        String strBook = getIntent().getStringExtra(Constants.CSV_STRING);

        edBook         = findViewById(R.id.edBookTitle);
        edReasonToRead = findViewById(R.id.edReasonToRead);

        getBook(strBook);
    }

    private void getBook(String strBook) {
        if (!strBook.isEmpty()){
            book = new Book(strBook);
            edBook.setText(book.getTitle());
            edReasonToRead.setText(book.getReasonToRead());

        }

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

}
