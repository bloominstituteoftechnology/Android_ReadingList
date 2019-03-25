package com.jakeesveld.readinglist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;
    LinearLayout layoutList;
    static ArrayList<Book> bookList;
    static int nextId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        layoutList = findViewById(R.id.layout_list);
        bookList = new ArrayList<>();
        bookList.add(createDummyBook());
        bookList.add(createDummyBook());
        bookList.add(createDummyBook());
        bookList.add(createDummyBook());

        for(Book book: bookList){
            layoutList.addView(createBookView(book));
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", nextId++);
                startActivityForResult(intent, 1);
            }
        });
    }

    public TextView createBookView(final Book addedBook){
        TextView newBook = new TextView(context);

        newBook.setText(addedBook.getTitle());
        if(addedBook.getHasBeenRead()){
            newBook.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        newBook.setTextSize(22);
        newBook.setPadding(10,10,10,10);
        newBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                String csvBook = addedBook.toCSVString();
                intent.putExtra("book", csvBook);
                startActivityForResult(intent, 1);
            }
        });


        return newBook;
    }


    public Book createDummyBook(){
        Book dummyBook = new Book(
                "Dummy Title",
                "It's a book for dummies",
                true,
                nextId++
        );

        return dummyBook;
    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
