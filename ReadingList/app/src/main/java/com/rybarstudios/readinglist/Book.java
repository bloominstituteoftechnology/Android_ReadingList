package com.rybarstudios.readinglist;

public class Book {

    private String title, reasonToRead;
    private boolean hasBeenRead;
    private int id;

    public Book(String title, String reasonToRead, boolean hasBeenRead, int id) {
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.hasBeenRead = hasBeenRead;
        this.id = id;
    }

    public Book(String csvFile) {
        //Possibly store csv string here then loop through and convert to proper data type
        //to pass into constructor
        //String.format("%d", "%s", "%s", "%b", id, title, reasonToRead, hasBeenRead);
        //TODO write constructor
        String[] values = csvFile.split(",");
        if(values.length == 4) {
            this.id = Integer.parseInt(values[0]);
            this.title = values[1];
            this.reasonToRead = values[2];
            this.hasBeenRead = Boolean.parseBoolean(values[3]);
        }
    }

    public String toCsvString(String idk) {
        //TODO write method

        return String.format("%d", "%s", "%s", "%s", id, title, reasonToRead, hasBeenRead);
    }

    public String getTitle() {
        return title;
    }

    public String getReasonToRead() {
        return reasonToRead;
    }

    public boolean isHasBeenRead() {
        return hasBeenRead;
    }

    public int getId() {
        return id;
    }
}
