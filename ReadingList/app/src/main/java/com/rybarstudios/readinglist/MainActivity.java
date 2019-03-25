package com.rybarstudios.readinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;
    Button addBook;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        layout = findViewById(R.id.view_book_list);
        addBook = findViewById(R.id.button_add_book);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                startActivity(intent);
            }
        });

    }

    private TextView buildItemView(Book book) {
        TextView view = new TextView(context);
        view.setText(book.getTitle() + " " + book.getReasonToRead());
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

        return view;
    }
}