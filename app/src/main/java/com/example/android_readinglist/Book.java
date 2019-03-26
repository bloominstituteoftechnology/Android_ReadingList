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
//    public void Book (String id){
//        this.id = id;

//    }
    Book(String csvBook){
        String[] bookStringList = csvBook.split(",");
        if(bookStringList.length >3)
         {
            id = bookStringList[0];
            title = bookStringList[1];
            reasonToRead = bookStringList[2];
            hasBeenRead = Boolean.parseBoolean(bookStringList[3]);
        }


    }
    public Book(){

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReasonToRead(String reasonToRead) {
        this.reasonToRead = reasonToRead;
    }

    public void setHasBeenRead(Boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
    }

    public void setId(String id){
        this.id = id;
    }
}
