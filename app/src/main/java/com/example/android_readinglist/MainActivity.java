package com.example.android_readinglist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    int readColor = Color.parseColor("#198c19");
    int unreadColor = Color.parseColor("#b20000");
    LinearLayout listLayout;
    ArrayList<Book> bookArrayList = new ArrayList<>();
    Button addButton;
    Context context;
    int nextId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        listLayout = findViewById(R.id.scroller_view);
/*
        bookArrayList.add(book1);
        bookArrayList.add(book2);
        bookArrayList.add(book3);
        bookArrayList.add(book4);
        bookArrayList.add(book5);
        bookArrayList.add(book6);
*/

        for(Book books:bookArrayList){
            buildItemView(books);        }


        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editActivity = new Intent (context, EditBookActivity.class);
                 int  id =listLayout.getChildCount();
                 String stringId = Integer.toString(id);
                editActivity.putExtra("id",stringId);
                 startActivity(editActivity);
            }
        });
    }
    private TextView buildItemView(Book bookView){

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
            }
        });
        return view;
    }
//    public void createBook(String id, String title, String reaonToRead, Boolean hasBeenRead)



Book book1 = new Book("1","Its beginning to look alot like work","goodbook",true);
Book book2 = new Book("2","Its beginning to look alot like work","goodbook",false);
Book book3 = new Book("3","Its beginning to look alot like work","goodbook",true);
Book book4 = new Book("4","Its beginning to look alot like work","goodbook",true);
Book book5 = new Book("5","Its beginning to look alot like work","goodbook",false);
Book book6 = new Book("6","Its beginning to look alot like work","goodbook",true);







}
