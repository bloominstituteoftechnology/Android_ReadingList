package com.vivekvishwanath.android_readinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Context context;
    Button addBookButton;
    LinearLayout bookViewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        addBookButton = findViewById(R.id.add_book_button);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                startActivity(intent);
            }
        });
        bookViewLayout = findViewById(R.id.book_view_layout);

    }

    private TextView buildItemView(Book book) {
        TextView bookView = new TextView(context);
        bookView.setText(book.getTitle());
        bookView.setTextSize(18);
        return bookView;
    }
}
