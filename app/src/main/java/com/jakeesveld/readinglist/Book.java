package com.jakeesveld.readinglist;

import java.io.Serializable;

public class Book implements Serializable {

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
        String[] values = csvObject.split(",");
        if(values.length == 4){
            this.title = values[0];
            this.reasonToRead = values[1].replace("~@", ",");
            if(values[2].equals("true")){
                this.hasBeenRead = true;
            }else{
                this.hasBeenRead = false;
            }
            this.id = Integer.parseInt(values[3]);
        }

    }

    public String toCSVString(){
        return String.format("%s,%s,%b,%d",
                title,
                reasonToRead.replace(",", "~@"),
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
