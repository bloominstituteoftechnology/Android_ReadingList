package com.example.israel.readinglist;

public class Book {

    public Book(String title, String reasonToRead, boolean bRead, String id) {
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.bRead = bRead;
        this.id = id;
    }

    /** @param csvStr must be a single csv row */
    public Book(String csvStr) {
        // @WARNING assumes that we only pass a csv row
        String[] columns = csvStr.split(",");

        if (columns.length > 3) { // should be at least 4 columns
            title = columns[0];
            reasonToRead = columns[1];
            bRead = columns[2].equals("1");
            id = columns[3];
        }

    }

    private String title;
    private String reasonToRead;
    private boolean bRead;
    private String id;

    public String getTitle() {
        return title;
    }

    public String getReasonToRead() {
        return reasonToRead;
    }

    public void setReasonToRead(String reasonToRead) {
        this.reasonToRead = reasonToRead;
    }

    public boolean isRead() {
        return bRead;
    }

    public void setRead(boolean bRead) {
        this.bRead = bRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toCSVString() {
        return title + "," + reasonToRead + "," + (bRead ? "1" : "0") + "," + id;
    }

    public void update(Book book) {
        this.title = book.title;
        this.reasonToRead = book.reasonToRead;
        this.bRead = book.bRead;
    }

}
