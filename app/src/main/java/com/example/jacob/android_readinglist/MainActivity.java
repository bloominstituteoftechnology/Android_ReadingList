package com.example.jacob.android_readinglist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences preferences;

    private Context context;
    private LinearLayout listLayout;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        preferences = this.getPreferences(Context.MODE_PRIVATE);

        context = this;
        listLayout = findViewById(R.id.layout_book_list);
        book = new Book(1, "Test Book Title1", "Test Reason to read1.", false);
        listLayout.addView(buildItemView(book));
        book = new Book(1, "Test Book Title2", "Test Reason to read2.", true);
        listLayout.addView(buildItemView(book));



/*        viewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        final Observer<ArrayList<Note>> observer = new Observer<ArrayList<Note>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Note> notes) {
                if (notes != null) {
                    refreshListView(notes);
                }

            }
        };
        viewModel.getNotesList().observe(this, observer);*/


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
//               Intent intent = new Intent(context, EditActivity.class);
//               intent.putExtra(EditActivity.EDIT_NOTE_KEY, book);
//               startActivityForResult(intent, EDIT_REQUEST_CODE);
            }
        });

        return textView;

    }
}
