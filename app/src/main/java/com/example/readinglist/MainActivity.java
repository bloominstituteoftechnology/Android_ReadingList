package com.example.readinglist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Context context;
    LinearLayout linearLayout;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.layout_linear_child);
        context = this;


        //Add a couple hardcoded books for testing
        Book book1 = new Book("0,The Martian,I like Martians,1");
        Book book2 = new Book("1,The Girl on The Train,I like Trains,1");
        linearLayout.addView(BuildItemView(book1));
        linearLayout.addView(BuildItemView(book2));

        button = findViewById(R.id.button_add_book);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditBookActivity.class);
                //handles the case of pushing the button
                String id = Integer.toString(linearLayout.getChildCount());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });



    }



    public TextView BuildItemView(final Book book){
        TextView tv = new TextView(context);
        tv.setText("Title: " + book.getTitle() + "\n Reason To Read: " + book.getReasonToRead() );
        tv.setTextSize(22);
        if (!book.isHasBeenRead()) {
            tv.setTypeface(Typeface.DEFAULT_BOLD);
        }
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditBookActivity.class);
                //handles the case of tapping an already existing book
                String temp = Book.toCsvString(book);
                intent.putExtra("BookCsv", temp);
                startActivity(intent);
            }
        });
        return tv;
    }
}
