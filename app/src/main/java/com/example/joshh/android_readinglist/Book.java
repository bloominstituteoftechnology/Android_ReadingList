package com.example.joshh.android_readinglist;

public class Book {
    private String title;
    private String reasonToRead;
    private String id;
    private boolean hasBeenRead;

    public Book(String title, String reasonToRead, String id, boolean hasBeenRead) {
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.id = id;
        this.hasBeenRead = hasBeenRead;
    }

    public Book(String csvString){
        String[] csvData = csvString.split(",");
        this.title = csvData[0];
        this.reasonToRead = csvData[1];
        this.id = csvData[2];
        this.hasBeenRead = Boolean.parseBoolean(csvData[3]);
    }

    public String getTitle() {
        return title;
    }

    public String getReasonToRead() {
        return reasonToRead;
    }

    public String getId() {
        return id;
    }

    public boolean isHasBeenRead() {
        return hasBeenRead;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReasonToRead(String reasonToRead) {
        this.reasonToRead = reasonToRead;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHasBeenRead(boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
    }

    public String toCsvString(){
        String bookString = this.getTitle() + "," + this.getReasonToRead() + "," + this.getId() + "," + this.hasBeenRead;
        return bookString;
    }


}
