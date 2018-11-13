package com.example.jacob.android_readinglist;

import java.io.Serializable;

public class Book implements Serializable {
    public static final int NO_ID = -1;

    public String title, reasonToRead;
    public boolean hasBeenRead;
    public int id;

    public Book(int id, String title, String reasonToRead, boolean hasBeenRead) {
        this.id = id;
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.hasBeenRead = hasBeenRead;
    }

    public Book(String csvString) {
        String[] values = csvString.split(",");
        this.id = Integer.parseInt(values[0]);
        this.title = values[1];
        this.reasonToRead = values[3];
        if (values[4].equals("true")) {
            this.hasBeenRead = true;
        } else {
            this.hasBeenRead = false;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReasonToRead() {
        return reasonToRead;
    }

    public void setReasonToRead(String reasonToRead) {
        this.reasonToRead = reasonToRead;
    }

    public boolean isHasBeenRead() {
        return hasBeenRead;
    }

    public void setHasBeenRead(boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toCSVString() {
     return this.id + "," + this.title.replaceAll(",","") + "," + this.reasonToRead.replaceAll(",","") + "," + Boolean.toString(this.hasBeenRead);
    }

}
