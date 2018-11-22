package com.thadocizn.readinglist.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.thadocizn.readinglist.BookController;
import com.thadocizn.readinglist.R;
import com.thadocizn.readinglist.ViewModel.BookModel;
import com.thadocizn.readinglist.classes.Constants;

public class MainActivity extends AppCompatActivity {

    Context context;
    Activity activity;
    ScrollView scrollView;
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences(Constants.COM_THADOCIZN_READING_LIST, Context.MODE_PRIVATE);
        context     = this;
        activity    = this;
        scrollView = findViewById(R.id.scrollView1);

        findViewById(R.id.btnAddBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(Constants.EDIT_BOOK_KEY, String.valueOf(BookModel.getAllBooks().size()));
                startActivityForResult(intent, Constants.EDIT_BOOK_REQUESTCODE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        scrollView.removeAllViews();
        scrollView.addView(BookController.getBooksView(context, activity));

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == Constants.EDIT_BOOK_REQUESTCODE){
                BookController.handleEditActivityResult(data);
            }
        }
    }
}
