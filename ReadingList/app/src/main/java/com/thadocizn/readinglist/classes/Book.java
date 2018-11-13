package com.thadocizn.readinglist.classes;

public class Book {
    private String title;
    private String reasonToRead;
    private Boolean hasBeenRead;
    private String id;

    public Book(String title, String reasonToRead, Boolean hasBeenRead, String id) {
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.hasBeenRead = hasBeenRead;
        this.id = id;
    }

    public Book(String csvString) {
        String[] values = csvString.split(",");
        this.title =values[0];
        this.reasonToRead =values[1];
        this.hasBeenRead = Boolean.valueOf(values[2]);
        this.id = values[3];
    }

    public String toCsvString(){
       return String.format("%s,%s,%b,%s".replaceAll(",", ""), this.title, this.reasonToRead, this.hasBeenRead, this.id);
    }

}
