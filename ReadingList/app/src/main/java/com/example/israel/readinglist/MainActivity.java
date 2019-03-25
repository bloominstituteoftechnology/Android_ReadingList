package com.example.israel.readinglist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_BOOK_CSV_STRING = "book csv string";
    public static final int REQUEST_EDIT_BOOK = 0;
    public static final String SP_NAME = "reading list";
    public static SharedPreferences preferences;
    private static ScrollView booksScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        booksScrollView = findViewById(R.id.scroll_view_books);

        preferences = getSharedPreferences(SP_NAME, MODE_PRIVATE); // @NOTE not the best way to access SP

        Button addBookButton = findViewById(R.id.button_add_book);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book unaddedBook = new Book("", "", false, SharedPrefsDAO.UNADDED_BOOK_ID);

                Intent intent = new Intent(MainActivity.this, EditBookActivity.class);
                intent.putExtra(KEY_BOOK_CSV_STRING, unaddedBook.toCSVString());
                startActivityForResult(intent, REQUEST_EDIT_BOOK);
            }
        });

        resetBooksView(); // populate books view
    }

    private void resetBooksView() {
        booksScrollView.removeAllViews();
        booksScrollView.addView(BooksController.getBooksView(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_EDIT_BOOK) {
            if (intent != null) {
                if (resultCode == RESULT_OK) { // submit. update/add edited book
                    BooksController.handleEditActivityResult(intent);
                    resetBooksView();
                } // else. cancel book editing, no need to do anything
            }
        }
    }
}
