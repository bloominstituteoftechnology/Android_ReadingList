package com.lambdaschool.android_readinglist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK_NEW_TAG = "new-book";
    public static final String EXTRA_BOOK_EDIT_TAG = "edit-book";
    public static final String BOOK_DISPLAY_SEPARATOR = ": ";
    public static final int PADDING_FOR_TEXTVIEW = 15;
    public static final int TEXTSIZE_FOR_TEXTVIEW = 22;
    LinearLayout linearLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        linearLayout = findViewById(R.id.linear_layout_books);
        populateViewOfBooks();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(EXTRA_BOOK_NEW_TAG, BookRepository.generateId());
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK/* && requestCode == 1*/) {
            if (data != null) {
                //super.onActivityResult(requestCode, resultCode, data);
                String bookInCsv = data.getStringExtra(EXTRA_BOOK_EDIT_TAG);
                Book bookToAdd = new Book(bookInCsv);
                BookRepository.addBookToList(bookToAdd);
                populateViewOfBooks();
            }
        }
    }

    public void populateViewOfBooks() {
//        linearLayout.addView(buildItemView(new Book("0,Harry Potter,Fun,False")));
//        linearLayout.addView(buildItemView(new Book("1,Where the Red Fern Grows,Adventurous,True")));
//        linearLayout.addView(buildItemView(new Book("45,Lamb of the Ages,Mind Expanding,False")));
//        linearLayout.addView(buildItemView(new Book("7000,Star Wars,A sage,True")));
        linearLayout.removeAllViews();
        for (int i =0; i < BookRepository.bookList.size(); i++) {
            linearLayout.addView(buildItemView(BookRepository.bookList.get(i)));
        }
    }

    public TextView buildItemView(final Book book) {
        TextView textview = new TextView(context);
        textview.setText(book.getId() + BOOK_DISPLAY_SEPARATOR + book.getTitle());
        textview.setPadding(PADDING_FOR_TEXTVIEW, PADDING_FOR_TEXTVIEW, PADDING_FOR_TEXTVIEW, PADDING_FOR_TEXTVIEW);
        textview.setTextSize(TEXTSIZE_FOR_TEXTVIEW);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(EXTRA_BOOK_EDIT_TAG, book.toCsvString());
                startActivityForResult(intent,1);
            }
        });
        return textview;
    }
}
