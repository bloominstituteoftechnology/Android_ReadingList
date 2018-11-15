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

import java.util.Random;

public class EditBookActivity extends AppCompatActivity {

    public static final String EDIT_BOOK_KEY = "edit_book";
    private EditText titleText, reasonText;
    private CheckBox hasReadCheck;
    private String id;
    private Button submitButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        titleText = findViewById(R.id.edit_title);
        reasonText = findViewById(R.id.edit_reason);
        hasReadCheck = findViewById(R.id.has_read);
        submitButton = findViewById(R.id.submit_button);
        cancelButton = findViewById(R.id.cancel_button);

        Intent intent = getIntent();
        id = intent.getStringExtra(EDIT_BOOK_KEY);
        String csvString = intent.getStringExtra("csv_string");
        if(csvString != null){
            Book book = new Book(csvString);
            String title = book.getTitle();
            String reason = book.getReasonToRead();
            id = book.getId();
            boolean hasRead = book.isHasBeenRead();

            titleText.setText(title);
            reasonText.setText(reason);
            hasReadCheck.setChecked(hasRead);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnData();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelEdit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        returnData();
        super.onBackPressed();
    }

    private void cancelEdit(){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    private void returnData() {
        String newBookTitle = titleText.getText().toString();
        String newReasonToRead = reasonText.getText().toString();
        boolean newHasRead = hasReadCheck.isChecked();
        String csvString = String.format("%s,%s,%s,%b", newBookTitle, newReasonToRead, id, newHasRead);
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EDIT_BOOK_KEY, csvString);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
