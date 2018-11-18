package com.thadocizn.readinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout parentLayout;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        parentLayout = findViewById(R.id.parentLayout);

        Book book = new Book("Cat in the Hat","Its a good book", "1",true);
        parentLayout.addView(getTextView(book));
        findViewById(R.id.btnAddBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(parentLayout.getChildCount());
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(Constants.KEY_ID, id);
                startActivity(intent);
            }
        });
    }

    private TextView getTextView(final Book book) {
        TextView textView = new TextView(this);
        textView.setText(book.getTitle());
        textView.setTextSize(24);
        textView.setPadding(10, 10, 10, 10);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(Constants.CSV_STRING, book.toCsvString());
                startActivity(intent);
            }
        });
        return textView;
    }

}
