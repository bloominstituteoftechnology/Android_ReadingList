package com.example.android_readinglist;

public class Book {
    private String title, reasonToRead, id;
    private Boolean hasBeenRead;

    public Book (String id, String title, String reasonToRead, Boolean hasBeenRead){
        this.id = id;
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.hasBeenRead = hasBeenRead;

    }
    public void createBook(String csvBook){
        String[] bookStringList = csvBook.split(",");
        id = bookStringList[0];
        title = bookStringList[1];
        reasonToRead = bookStringList[2];
        hasBeenRead = Boolean.parseBoolean(bookStringList[3]);

    }
    String toCsvString(){
        return String.format("%s,%s,%s,%b",id,title,reasonToRead,hasBeenRead);

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

    public Boolean getHasBeenRead() {
        return hasBeenRead;
    }
}
