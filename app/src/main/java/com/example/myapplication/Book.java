package com.example.myapplication;

public class Book{


    public static String TAG = "Book";

    private String  title;
    private String  reasonToRead;
    private String  id;
    private boolean hasBeenRead;

    public Book(String id, String title, String reasonToRead, boolean hasBeenRead){
        this.id           = id;
        this.title        = title;
        this.reasonToRead = reasonToRead;
        this.hasBeenRead  = hasBeenRead;

    }

    public Book(String csvString){
        String[] csvToString = csvString.split(",");
        this.id = csvToString[0];
        this.title = csvToString[1];
        this.reasonToRead = csvToString[2];

        if(csvToString[3] == "read"){
            this.hasBeenRead = true;
        }else{
            this.hasBeenRead = false;}

    }

    public String toCsvString(){
        String hasBeenReadString;
        if(hasBeenRead){
            hasBeenReadString = "read";
        } else{hasBeenReadString = "notRead";}


        String csvString = String.format("%s,%s,%s,%s", id,title,reasonToRead,hasBeenReadString);

        return csvString;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasBeenRead() {
        return hasBeenRead;
    }

    public void setHasBeenRead(boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
    }
}
