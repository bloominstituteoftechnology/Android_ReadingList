package com.lambda.readinglist;

import java.util.ArrayList;

public class BooksModel {
    private SharedPrefsDao spd;
    private static String strCurrentID;
    BooksModel(SharedPrefsDao spd){
        this.spd=spd;
        strCurrentID=spd.getInitialID();
    }
    public ArrayList<Book> AllBook(){
        return this.spd.AllBook();

    }

    public Book SigleBook(String strID){
        strCurrentID=strID;
        return spd.bkBookByID( strID);
    }

    public String NextID(){
        return spd.getNextId( this.strCurrentID );
    }
    public void update(Book bk){
        spd.updateBook( bk );

    }

}
