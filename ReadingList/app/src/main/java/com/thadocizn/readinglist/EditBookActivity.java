package com.thadocizn.readinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity {
    EditText edBook;
    EditText edReasonToRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        String strId  = getIntent().getStringExtra(Constants.KEY_ID);
        String strBook = getIntent().getStringExtra(Constants.CSV_STRING);

        edBook         = findViewById(R.id.edBookTitle);
        edReasonToRead = findViewById(R.id.edReasonToRead);

        getBook(strBook);
    }

    private void getBook(String strBook) {
        if (!strBook.isEmpty()){
            Book book = new Book(strBook);
            edBook.setText(book.getTitle());
            edReasonToRead.setText(book.getReasonToRead());

        }
    }
}
