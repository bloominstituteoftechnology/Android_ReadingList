package com.vivekvishwanath.android_readinglist;

public class Book {
    private String id;
    private String title;
    private String reasonToRead;
    private boolean hasBeenRead;

    public Book(String id, String title, String reasonToRead, boolean hasBeenRead) {
        this.id = id;
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.hasBeenRead = hasBeenRead;
    }

    public Book (String csvString) {
        String[] bookValues = csvString.split(",");
        if (bookValues.length == 5) {
            this.id = bookValues[0];
            this.title = bookValues[1].replace("~@", ",");
            this.reasonToRead = bookValues[2].replace("~@", ",");
            if (bookValues[3] == "read")
                this.hasBeenRead = true;
            else
                this.hasBeenRead = false;
        }
    }

    String toCsvString() {
        return String.format("%s,%s,%s,%b",
                id,
                title.replace(",", "~@"),
                reasonToRead.replace(",", "~@"),
                hasBeenRead ? "true" : "false");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
