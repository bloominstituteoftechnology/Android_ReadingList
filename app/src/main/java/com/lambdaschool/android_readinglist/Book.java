package com.lambdaschool.android_readinglist;

import java.io.Serializable;

public class Book implements Serializable {
    public static final String REPLACEMENT_FOR_COMMAS = "~@";
    private String id, title, reasonToRead;
    private boolean hasBeenRead;

    public Book() {
        this.id = BookRepository.generateId();
        this.title = "";
        this.reasonToRead = "";
        this.hasBeenRead = false;
    }

    public Book(String id, String title, String reasonToRead, boolean hasBeenRead) {
        this.id = id;
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.hasBeenRead = hasBeenRead;
    }

    public Book(String csvString) {
        String[] sections = csvString.split(",");

        this.id = sections[0];
        this.title = sections[1].replace(REPLACEMENT_FOR_COMMAS, ",");
        this.reasonToRead = sections[2].replace(REPLACEMENT_FOR_COMMAS, ",");
        this.hasBeenRead = Boolean.parseBoolean(sections[3]);
    }

    public String toCsvString() {
        return String.format("%s,%s,%s,%b", id, title.replace(",", REPLACEMENT_FOR_COMMAS), reasonToRead.replace(",", "~@"), hasBeenRead);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReasonToRead() {
        return reasonToRead;
    }

    public boolean hasBeenRead() {
        return hasBeenRead;
    }
}
