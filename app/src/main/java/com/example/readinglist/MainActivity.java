package com.example.readinglist;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Context context;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.layout_linear_child);

        context = this;
        Book book1 = new Book("0,The Martian,I like Martians,1");
        Book book2 = new Book("1,The Girl on The Train,I like Trains,1");

        linearLayout.addView(BuildItemView(book1));
        linearLayout.addView(BuildItemView(book2));



    }

    public TextView BuildItemView(Book book){
        TextView tv = new TextView(context);
        tv.setText("Title: " + book.getTitle() + "\n Reason To Read: " + book.getReasonToRead() );
        tv.setTextSize(22);
        if (!book.isHasBeenRead()) {
            tv.setTypeface(Typeface.DEFAULT_BOLD);
        }
        return tv;
    }
}
