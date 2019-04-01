package com.example.readinglist;

import java.io.Serializable;

public class Book implements Serializable {
    String title;
    String reasonToRead;
    boolean hasBeenRead;
    String id;

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

    public boolean isHasBeenRead() {
        return hasBeenRead;
    }

    public void setHasBeenRead(boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book(String title, String reasonToRead, boolean hasBeenRead, String id) {
        this.title = title;
        this.reasonToRead = reasonToRead;
        this.hasBeenRead = hasBeenRead;
        this.id = id;
    }
    public Book(String csvString) {
            String[] values = csvString.split(",");
            if (values.length == 5) {
                try {
                    this.id = values[0];
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                this.reasonToRead = values[1];
                try {
                    this.hasBeenRead = Boolean.parseBoolean(values[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        public String toCsvString() {
            return String.format(
                    "%d,%t,%r,%h",
                    id,
                    title,
                    reasonToRead,
                    hasBeenRead
            );
        }
    }

