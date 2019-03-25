package com.jakeesveld.readinglist;

public class Book {

    private String title, reasonToRead;
    private Boolean hasBeenRead;
    private int id;

    public Book(String title, String reasonToRead, Boolean hasBeenRead, int id) {
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.hasBeenRead = hasBeenRead;
        this.id = id;
    }

    public Book(String csvObject){

    }

    public String toCSVString(){
        return String.format("%s, %s, %b, %d",
                title,
                reasonToRead,
                hasBeenRead,
                id);
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

    public Boolean getHasBeenRead() {
        return hasBeenRead;
    }

    public void setHasBeenRead(Boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
