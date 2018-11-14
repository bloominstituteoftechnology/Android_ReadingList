package com.example.jacob.android_readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences preferences;
    public static final String DEFAULT_SHARE_PREFERENCES_KEY = "default_share_preferences";

    private Context context;
    private LinearLayout layoutList;
    //    private BookViewModel viewModel;
    public static final int EDIT_REQUEST_CODE = 1;
    private Book book;
//    Book(int id, String title, String reasonToRead, boolean hasBeenRead)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        layoutList = findViewById(R.id.layout_book_list);
        preferences = this.getSharedPreferences(DEFAULT_SHARE_PREFERENCES_KEY, Context.MODE_PRIVATE);
//        preferences = this.getPreferences(Context.MODE_PRIVATE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                int id = layoutList.getChildCount();
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(EditBookActivity.EDIT_BOOK_KEY, id);
                startActivityForResult(intent, EDIT_REQUEST_CODE);

            }
        });



/*        book = new Book(1, "Test Book Title1", "Test Reason to read1.", false);
        layoutList.addView(buildItemView(book));
        book = new Book(1, "Test Book Title2", "Test Reason to read2.", true);
        layoutList.addView(buildItemView(book));*/

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

    private TextView buildItemView(final Book book) {
        TextView textView = new TextView(context);
        textView.setText(book.getTitle());
        textView.setTextSize(24);
        textView.setPadding(10, 10, 10, 10);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra(EditBookActivity.EDIT_BOOK_KEY, book.toCSVString());
                startActivityForResult(intent, EDIT_REQUEST_CODE);
            }
        });

        return textView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String returnedString = data.getStringExtra(EditBookActivity.EDIT_BOOK_KEY);
                Book returnedBook = new Book(returnedString);
                SharedPrefsDao.updateBook(returnedBook);
                refreshListView();
            }
        }
    }


    private void refreshListView() {
        layoutList.removeAllViews();

        String[] allIds = SharedPrefsDao.getAllBookIds();
        if (allIds != null) {
            for (String id : allIds) {
                layoutList.addView(buildItemView(SharedPrefsDao.getBook(id)));
            }
        }
    }
}
