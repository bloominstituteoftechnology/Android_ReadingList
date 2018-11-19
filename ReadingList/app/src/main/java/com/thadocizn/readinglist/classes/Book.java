package com.thadocizn.readinglist.classes;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String reasonToRead;
    private String id;
    private boolean hasBeenRead;

    public String toCsvString(){
        return String.format("%s,%s,%s,%b".replaceAll(" ", ","), this.title, this.reasonToRead, this.id, this.hasBeenRead);
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

    public Book(String csv) {
        String[] csvs = csv.split(",");
        this.title = csvs[0];
        this.reasonToRead = csvs[1];
        this.id = csvs[2];
        this.hasBeenRead = Boolean.parseBoolean(csvs[3]);
    }

    public Book(String title, String reasonToRead, String id, boolean hasBeenRead) {

        this.title = title;
        this.reasonToRead = reasonToRead;
        this.id = id;
        this.hasBeenRead = hasBeenRead;
    }

}
