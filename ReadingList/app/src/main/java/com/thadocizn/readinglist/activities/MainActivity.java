package com.thadocizn.readinglist.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.readinglist.R;
import com.thadocizn.readinglist.activities.EditBookActivity;
import com.thadocizn.readinglist.classes.Book;
import com.thadocizn.readinglist.classes.Constants;

public class MainActivity extends AppCompatActivity {

    LinearLayout parentLayout;
    Context context;
    public static SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences(Constants.COM_THADOCIZN_READING_LIST, Context.MODE_PRIVATE);
        context      = this;
        parentLayout = findViewById(R.id.parentLayout);

        Book book = new Book("Cat in the Hat","Its a good book", "1",true);
        parentLayout.addView(getTextView(book));
        findViewById(R.id.btnAddBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id     = String.valueOf(parentLayout.getChildCount());
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(Constants.KEY_ID, id);
                startActivityForResult(intent, Constants.EDIT_BOOK_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == Constants.EDIT_BOOK_CODE){
                String book = getIntent().getStringExtra(Constants.STRING_BOOK);
                Book newBook = new Book(book);
                parentLayout.addView(getTextView(newBook));
            }
        }
    }
}
