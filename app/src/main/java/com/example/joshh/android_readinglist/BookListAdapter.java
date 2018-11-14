package com.example.joshh.android_readinglist;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookTitle;
        ViewGroup parentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookTitle = itemView.findViewById(R.id.book_element_title);
            parentView = itemView.findViewById(R.id.book_element_parent_layout);
        }
    }


    private ArrayList<Book> booksDataList;
    private Context context;
    private Activity activity;

    BookListAdapter(ArrayList<Book> booksDataList, Activity activity){
        this.booksDataList = booksDataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_element_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Book book = booksDataList.get(i);

        viewHolder.bookTitle.setText(book.getTitle());
        //TODO; SEND DATA TO EDIT ACTIVITY
    }

    @Override
    public int getItemCount() {
        return booksDataList.size();
    }
}
