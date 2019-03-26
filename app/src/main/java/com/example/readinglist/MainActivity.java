package com.example.readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String MY_PREFERENCES = "myPreferences";
    public static final int EDIT_REQUEST_CODE = 44;
    public static final int ADD_REQUEST_CODE = 55;
    public static Context context;
    LinearLayout linearLayout;
    Button button;
    ArrayList<Book> bookList;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.layout_linear_child);
        context = this;
        bookList = new ArrayList<>();
        preferences = this.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);


        //Add a couple hardcoded books for testing
//        bookList.add(new Book("0,The Martian,I like Martians,1"));
//        bookList.add(new Book("1,The Girl on The Train,I like Trains,1"));
        bookList = BooksModel.findAllBooks();
        for (Book book: bookList) {
            linearLayout.addView(BuildItemView(book));
        }

        button = findViewById(R.id.button_add_book);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditBookActivity.class);
                //handles the case of pushing the button
                int temp = BooksModel.findNextBookId();
                String id = Integer.toString(temp);
                intent.putExtra("id", id);
                intent.putExtra("RequestCode", ADD_REQUEST_CODE);
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
//        for (Book book: bookList) {
//            linearLayout.addView(BuildItemView(book));
//        }

    }

    public TextView BuildItemView(final Book book){
        TextView tv = new TextView(context);
        tv.setText("Title: " + book.getTitle() + "\n Reason To Read: " + book.getReasonToRead() );
        tv.setTextSize(22);
        tv.setId(Integer.parseInt(book.getId()));
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
                intent.putExtra("RequestCode", EDIT_REQUEST_CODE);
                startActivityForResult(intent, EDIT_REQUEST_CODE);
            }
        });
        return tv;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Log.i("LogTest", "onactivityresult ran successfully");
        //if (true) {
        if ((requestCode == 55 || requestCode == 44) && resultCode == Activity.RESULT_OK) {
            String temp = data.getStringExtra("BOOK_KEY");
            Book book = new Book(temp);
            BooksModel.saveBook(book);
            int indexTemp = Integer.parseInt(book.getId());
            //Will decide whether to add the book to the list, or to change a currently held book
            if (bookList.size() <= indexTemp){
                bookList.add(book);
            } else {
                bookList.set (indexTemp, book);
            }
            bookList = BooksModel.findAllBooks();
            linearLayout.removeAllViews();
            for (Book bookTemp : bookList) {
                linearLayout.addView(BuildItemView(bookTemp));
            }

        }
    }
}
