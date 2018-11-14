package com.example.jacob.android_readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences preferences;
    public static final String DEFAULT_SHARE_PREFERENCES_KEY = "default_share_preferences";


    private Context context;
    private LinearLayout layoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        preferences = this.getSharedPreferences(DEFAULT_SHARE_PREFERENCES_KEY, Context.MODE_PRIVATE);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(EditBookActivity.EDIT_BOOK_KEY, Book.NO_ID);
                startActivityForResult(intent, Constants.EDIT_REQUEST_CODE);

            }
        });

        updateScrollView();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.EDIT_REQUEST_CODE) {
                if (data != null) {
                    String returnedString = data.getStringExtra(EditBookActivity.EDIT_BOOK_KEY);
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra(Constants.UPDATE_REQUEST_CODE, returnedString);
                    BooksController.handleEditActivityResult(intent);
                    updateScrollView();
                }
            }
        }
    }

    private void updateScrollView() {
        ScrollView scrollView = findViewById(R.id.scrollview_list);
        scrollView.removeAllViews();
        layoutList = BooksController.getBooksView(this);
        scrollView.addView(layoutList);

    }
}
