package com.thadocizn.readinglist.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.thadocizn.readinglist.R;
import com.thadocizn.readinglist.classes.Book;
import com.thadocizn.readinglist.classes.Constants;

public class EditBookActivity extends AppCompatActivity {
    EditText edBook;
    EditText edReasonToRead;
    CheckBox checkBox;
    String strId;
    String strBook;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        preferences = this.getSharedPreferences(Constants.COM_THADOCIZN_READING_LIST, Context.MODE_PRIVATE);

        Intent intent = getIntent();
        strId   = intent.getStringExtra(Constants.EDIT_BOOK_KEY);
        strBook = intent.getStringExtra(Constants.CSV_STRING);

        edBook         = findViewById(R.id.edBookTitle);
        edReasonToRead = findViewById(R.id.edReasonToRead);
        checkBox = findViewById(R.id.checkboxReadBook);




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

        if(strBook != null){
            Book book = new Book(strBook);
            String name = book.getTitle();
            String reason = book.getReasonToRead();
            strId = book.getId();
            boolean hasRead = book.isHasBeenRead();

            edBook.setText(name);
            edReasonToRead.setText(reason);
            checkBox.setChecked(hasRead);
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
        boolean bool = checkBox.isChecked();

        String strBook = String.format("%s,%s,%s,%b", strTitle, strReasonToRead, strId, bool);

        Intent resultIntent = new Intent();
        resultIntent.putExtra(Constants.EDIT_BOOK_KEY, strBook);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

    private void cancelData(){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

}
