package com.jakeesveld.readinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class EditActivity extends AppCompatActivity {
    EditText editBookTitle;
    EditText editBookReason;
    Switch switchHasRead;
    Book currentBook;
    Button buttonSubmit;
    Button buttonCancel;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editBookTitle = findViewById(R.id.edit_book_title);
        editBookReason = findViewById(R.id.edit_book_reason);
        switchHasRead = findViewById(R.id.switch_has_read);
        buttonSubmit = findViewById(R.id.button_submit);
        buttonCancel = findViewById(R.id.button_cancel);
        context = this;

        Intent intent = getIntent();
        if(intent.hasExtra("id")){
            int newBookId = intent.getIntExtra("id", -1);
            currentBook = new Book("", "", false, newBookId);
        }

        if(intent.hasExtra("book")) {
            String passedBookCSV = intent.getStringExtra("book");
                currentBook = new Book(passedBookCSV);
                editBookTitle.setText(currentBook.getTitle());
                editBookReason.setText(currentBook.getReasonToRead());
                switchHasRead.setChecked(currentBook.getHasBeenRead());
        }

        switchHasRead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    currentBook.setHasBeenRead(true);
                }else{
                    currentBook.setHasBeenRead(false);
                }
            }
        });

        editBookTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentBook.setTitle(editBookTitle.getText().toString());
            }
        });

        editBookReason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentBook.setReasonToRead(editBookReason.getText().toString());
            }
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnData(currentBook);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelEdit();
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        returnData(currentBook);
    }

    private void returnData(Book editedBook){
        String returnedBook = editedBook.toCSVString();
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("returned_book", returnedBook);
        setResult(RESULT_OK);
        finish();
    }

    private void cancelEdit(){
        Intent intent = new Intent(context, MainActivity.class);
        setResult(RESULT_CANCELED);
        finish();
    }

}
