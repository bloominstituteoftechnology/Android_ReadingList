package com.example.android_readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    public static final int REQUEST_CODE_ADD = 123;
    public static final String SHARED_PREFERENCE_NAME = "firstSharedPreference";
    int readColor = Color.parseColor("#198c19");
    int unreadColor = Color.parseColor("#b20000");
    LinearLayout listLayout;
    ArrayList<Book> bookArrayList;
    Context context;
    public static SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSharedPreferences(SHARED_PREFERENCE_NAME,MODE_PRIVATE);
        context = this;
        listLayout = findViewById(R.id.scroller_view);
        bookArrayList = new ArrayList<>();




        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editActivity = new Intent (context, EditBookActivity.class);
                 int  id =listLayout.getChildCount();
                 String stringId = Integer.toString(id);
                 editActivity.putExtra("id",stringId);
                 startActivityForResult(editActivity, REQUEST_CODE_ADD);
            }
        });
    }
    private TextView buildItemView(final Book bookView){

        TextView view = new TextView(getApplicationContext());
        listLayout.addView(view);
        view.setText(bookView.getTitle());
        Boolean readStatus = bookView.getHasBeenRead();
        if(readStatus){
            view.setTextColor(readColor);
        }else{view.setTextColor(unreadColor);}
        view.setTextSize(20);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent sendBook = new Intent(context, EditBookActivity.class);
            String bookViewString = bookView.toCsvString();
            sendBook.putExtra("editBook", bookViewString);
            startActivityForResult(sendBook, REQUEST_CODE_ADD);
            }
        });
        return view;
    }
//    public void createBook(String id, String title, String reaonToRead, Boolean hasBeenRead)


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_ADD){
            if (data!=null){
//                data = getIntent();
                String returnedBookString = data.getStringExtra("returnedBook");
                Book book = new Book(returnedBookString);
                bookArrayList.add(book);

            }
            for(Book books:bookArrayList){
               listLayout.removeAllViews();
                buildItemView(books);        }
        }


    }
    {Book book1 = new Book("1","Its beginning to look alot like work","goodbook",true);
Book book2 = new Book("2","Its beginning to look alot like work","goodbook",false);
Book book3 = new Book("3","Its beginning to look alot like work","goodbook",true);
Book book4 = new Book("4","Its beginning to look alot like work","goodbook",true);
Book book5 = new Book("5","Its beginning to look alot like work","goodbook",false);
Book book6 = new Book("6","Its beginning to look alot like work","goodbook",true);}







}
