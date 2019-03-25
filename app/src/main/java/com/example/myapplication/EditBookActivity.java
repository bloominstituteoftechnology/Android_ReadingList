package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

public class EditBookActivity extends AppCompatActivity {

    EditText tvReasonsToRead;
    EditText tvTitle;
    Switch hasBeenRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
    }

}
