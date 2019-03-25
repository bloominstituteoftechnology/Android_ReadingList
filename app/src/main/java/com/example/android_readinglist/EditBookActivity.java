package com.example.android_readinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class EditBookActivity extends AppCompatActivity {
    private String title, reasonToRead, id;
    private Boolean hasBeenRead;
    private Book thisBook, editableBook;
    private String bookString;
    private EditText bookNameText, reasonToReadText;
    private Switch readSwitch;
    private TextView idText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        bookNameText = findViewById(R.id.book_name);
        reasonToReadText = findViewById(R.id.reason_to_read_view);
        idText = findViewById(R.id.book_id_text);
        readSwitch = findViewById(R.id.read_switch);


//,id,title,reasonToRead,hasBeenRead


        Intent bookIntent = getIntent();
        id = bookIntent.getStringExtra("id");
        //if sending a book to edit
        if (bookIntent.getStringExtra("editBook") != null) {
            bookString = bookIntent.getStringExtra("editBook");
            editableBook = thisBook.createBook(bookString);
            editableBook.setId(id);
            bookNameText.setText(editableBook.getTitle());
            idText.setText(editableBook.getId());
            readSwitch.setChecked(editableBook.getHasBeenRead());
            reasonToReadText.setText(editableBook.getReasonToRead());
        }

        //if creating new book
        else {
            editableBook = new Book(id);
        }


        //listener for data changes in title
        bookNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                title = s.toString();
            }
        });
        //listener for data changes in why to read

        reasonToReadText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                reasonToRead = s.toString();
            }
        });
//            public Book (String id, String title, String reasonToRead, Boolean hasBeenRead)

    }








    private String returnData(){
        editableBook.setTitle(title);
        editableBook.setReasonToRead(reasonToRead);
        editableBook.setHasBeenRead(readSwitch.isChecked());
        String returnBookAsString = editableBook.toCsvString();
        return returnBookAsString;
    }







}
