package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

public class EditBookActivity extends AppCompatActivity {

    Context context;
    EditText tvReasonsToRead;
    EditText tvTitle;
    CheckBox hasBeenRead;
    Button btnSubmit;
    Button btnCancel;
    String id;
    String eb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        btnSubmit = findViewById(R.id.btn_add_book);
        btnCancel = findViewById(R.id.btn_cancel);
        tvReasonsToRead = findViewById(R.id.tv_reasons);
        tvTitle = findViewById(R.id.tv_title);
        hasBeenRead = findViewById(R.id.is_read_box);
        eb = "not";

        context = this;
        Intent intent = getIntent();
        eb = intent.getStringExtra("editBook");
        if (intent.getStringExtra(Book.TAG) != null) {
            if (intent.getStringExtra("editBook") != null) {
                if (eb.equals("editBook")) {
                    Book book = new Book(intent.getStringExtra(Book.TAG));
                    id = book.getId();
                    populateFields(book);
                }
            }

        } else {
            this.id = intent.getStringExtra(Book.TAG);
        }


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnData();
            }
        });

    }

    public void populateFields(Book book) {
        tvTitle.setText(book.getTitle());
        tvReasonsToRead.setText(book.getReasonToRead());
        this.hasBeenRead.setChecked(book.isHasBeenRead());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        returnData();
    }

    public void returnData() {
        Book book = new Book(
                id,
                tvTitle.getText().toString(),
                tvReasonsToRead.getText().toString(),
                hasBeenRead.isChecked());

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        intent.putExtra(Book.TAG, book.toCsvString());

        finish();
    }
}
