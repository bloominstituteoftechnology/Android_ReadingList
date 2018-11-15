package com.example.joshh.android_readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int EDIT_BOOK_REQUESTCODE = 1;
    public static final String SHARED_PREFS = "prefs";
    private Activity activity;
    Context context;
    private ScrollView scrollView;
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        context = this;
        activity = this;
        scrollView = findViewById(R.id.books_list_parent);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createBookIntent = new Intent(context, EditBookActivity.class);
                createBookIntent.putExtra(EditBookActivity.EDIT_BOOK_KEY, Integer.toString(BooksModel.getAllBooks().size()));
                startActivityForResult(createBookIntent, EDIT_BOOK_REQUESTCODE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        scrollView.removeAllViews();
        scrollView.addView(BooksController.getBooksView(context, activity));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == EDIT_BOOK_REQUESTCODE){
                BooksController.handleEditActivityResult(data);
            }
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
}
