package com.lambdaschool.android_readinglist;

public class Book {
    private String title;
    private String reasonToRead;
    private boolean hasBeenRead;
    private String id;

    public Book(String title, String reasonToRead, boolean hasBeenRead, String id) {
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.hasBeenRead = hasBeenRead;
        this.id = id;
    }

    public Book(String csvString) {

    }
}
