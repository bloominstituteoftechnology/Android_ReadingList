package com.example.jacob.android_readinglist;

import java.io.Serializable;

public class Book implements Serializable {
    public static final int NO_ID = -1;

    private String title, reasonToRead;
    private boolean hasBeenRead;
    private int id;

    public Book(int id, String title, String reasonToRead, boolean hasBeenRead) {
        this.id = id;
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.hasBeenRead = hasBeenRead;
    }

    public Book(String csvString) {
        String[] values = csvString.split(",");
        this.id = Integer.parseInt(values[1]);
        this.title = values[0];
        this.reasonToRead = values[1];

    }


}
