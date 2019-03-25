package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Context context;
    private LinearLayout ll;
    private static int REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        ll = findViewById(R.id.ll_scrollview);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(Book.TAG, String.valueOf(ll.getChildCount()));
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        String bookString1 = "1,The Great Escape1,It's a good book,read";
        String bookString2 = "1,The Great Escape2,It's a good book,read";
        String bookString3 = "1,The Great Escape3,It's a good book,read";

        Book b1 = new Book(bookString1);
        Book b2 = new Book("1","The Great Escape","It's a good book",true);
        Book b3 = new Book(bookString3);

        ll.addView(buildItemView(b1));
        ll.addView(buildItemView(b2));
        ll.addView(buildItemView(b3));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            Book book = new Book(data.getStringExtra(Book.TAG));

           // if(doesBookIdExist(book) == false) {
                ll.addView(buildItemView(book));
          //  }
/*            else{
                EditText et = ll.findViewById(Integer.parseInt(book.getId()));
                et.setText(book.getTitle());
            }*/
        }
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

    public TextView buildItemView(Book book){
        final String csvString = book.toCsvString();
        TextView tv = new TextView(context);
        tv.setText(book.getTitle());
        tv.setTextSize(24);
        tv.setPadding(10,5,10,5);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra("editBook", "editBook");
                intent.putExtra(Book.TAG, csvString);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        return tv;
    }

    public boolean doesBookIdExist(Book book){
        int bookID = Integer.parseInt(book.getId());

        if(ll.findViewById(bookID) != null)
        {
            return true;
        }
        else{return false;}

    }


}
