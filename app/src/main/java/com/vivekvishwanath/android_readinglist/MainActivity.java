package com.vivekvishwanath.android_readinglist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
    }

    private TextView buildItemView(Book book) {
        TextView bookView = new TextView(context);
        bookView.setText(book.getTitle());
        bookView.setTextSize(18);
        return bookView;
    }
}
