package com.example.android_readin_list;

public class Book
{
    String title, ReasonToRead, id;
    Boolean hasbeenRead;

    public Book(String title, String reasonToRead, String id, Boolean hasbeenRead) {
        this.title = title;
        ReasonToRead = reasonToRead;
        this.id = id;
        this.hasbeenRead = hasbeenRead;
    }
}

