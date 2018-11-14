package com.example.joshh.android_readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity {

    public static final String EDIT_BOOK_KEY = "book_key";
    public static final String ADD_BOOK_KEY = "add_book_key";

    private String title, reasonToRead, id;
    private boolean hasRead;

    EditText editTitle, editReasonToRead;
    CheckBox checkHasRead;
    Button submitButton, cancelButton;

    Book book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        editTitle = findViewById(R.id.edit_book_title);
        editReasonToRead = findViewById(R.id.edit_reason_to_read);
        checkHasRead = findViewById(R.id.check_have_read);
        submitButton = findViewById(R.id.add_button);
        cancelButton = findViewById(R.id.cancel_button);

        Intent intent = getIntent();
        id = intent.getStringExtra(ADD_BOOK_KEY);
        String bookCsv = intent.getStringExtra(EDIT_BOOK_KEY);
        if(bookCsv != null){
            String[] stringArray = bookCsv.split(",");
            book = new Book(stringArray[0], stringArray[1], stringArray[2], Boolean.parseBoolean(stringArray[3]));
            title = book.getTitle();
            reasonToRead = book.getReasonToRead();
            id = book.getId();
            hasRead = book.isHasBeenRead();
        }
        editTitle.setText(title);
        editReasonToRead.setText(reasonToRead);
        checkHasRead.setChecked(hasRead);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnData();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBookEdit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        returnData();
        super.onBackPressed();
    }

    private void returnData(){
        String newBookTitle = editTitle.getText().toString();
        String newReasonToRead = editReasonToRead.getText().toString();
        boolean newHasRead = checkHasRead.isChecked();
        String csvString = String.format("%s,%s,%s,%b", newBookTitle, newReasonToRead, id, newHasRead);
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EDIT_BOOK_KEY, csvString);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    private void cancelBookEdit(){
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, resultIntent);
        finish();
    }
}
