package com.example.israel.readinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity {

    private EditText titleEditText;
    private CheckBox isReadCheckBox;
    private EditText reasonToReadEditText;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        titleEditText = findViewById(R.id.edit_text_title);
        isReadCheckBox = findViewById(R.id.check_box_is_read);
        reasonToReadEditText = findViewById(R.id.edit_text_reason_to_read);

        Intent intent = getIntent();
        String bookStr = intent.getStringExtra(MainActivity.KEY_BOOK_CSV_STRING);
        if (bookStr != null) {
            Book book = new Book(bookStr);
            // show the book's current data to the view
            titleEditText.setText(book.getTitle());
            isReadCheckBox.setChecked(book.isRead());
            reasonToReadEditText.setText(book.getReasonToRead());
            id = book.getId();
        }

        // cancel
        Button cancelButton = findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnEmpty();
            }
        });

        // submit
        Button submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnData();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed(); TODO should this be called even if finish() will be called
        returnData();
    }

    private void returnEmpty() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void returnData() {
        Book book = new Book(
                titleEditText.getText().toString(),
                reasonToReadEditText.getText().toString(),
                isReadCheckBox.isChecked(),
                id
        );

        Intent intent = new Intent();
        intent.putExtra(MainActivity.KEY_BOOK_CSV_STRING, book.toCSVString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
