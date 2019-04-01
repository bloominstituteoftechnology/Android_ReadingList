package com.example.readinglist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Context context;
    Button addBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        LinearLayout lineMan = (LinearLayout) findViewById(R.id.line_man);

        Button addBttn = findViewById(R.id.add_book);
        addBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditBookActivity.class);
                startActivityForResult(i, 0);
            }
        });
    }

    private TextView buildItemView(final Book b) {
        TextView textView = new TextView(context);
        textView.setTextSize(25);
        textView.setTextColor(Color.BLACK);
        textView.setText(String.format("Title: %t ", b.getTitle()));
        return textView;
    }




}