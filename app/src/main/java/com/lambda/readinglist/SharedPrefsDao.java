package com.lambda.readinglist;

import java.util.ArrayList;

public class SharedPrefsDao {
    private static ArrayList<Book> alBook;


    public SharedPrefsDao(){
        int iInitialCapacity=10;
        this.alBook=new ArrayList<Book>(iInitialCapacity);

    }
    public ArrayList<Book> AllBook(){
        return alBook;
    }
    public String[] getAllBookIds(){
        String[] straryAllTheID=new String[this.alBook.size()];
        for(int i=0;i<this.alBook.size();i++){
            straryAllTheID[i]=alBook.get(i).getStrID();

        }
        return straryAllTheID;
    }
    public String getNextId(String strCurrentID){

        String[] straryAllTheID=new String[this.alBook.size()];
        for(int i=0;i<this.alBook.size();i++){
            if (alBook.get(i).getStrID().equals( strCurrentID )){
                if(this.alBook.size()-1==i){
                    return  "";
                }else{
                    return alBook.get(i+1).getStrID();
                }

            }
        }
        return "Invalid ID";

    }
    public String getInitialID(){
        if (alBook.size()==0)return "new";
        return alBook.get(0).strID;
    }

    public Book bkBookByID(String strCurrentID){
        for(int i=0;i<this.alBook.size();i++){
            if(alBook.get(i).getStrID().equals( strCurrentID )){
                return alBook.get( i );
            }
        }
        return null;
    }
    public String strBookCSVByID(String strCurrentID){
        for(int i=0;i<this.alBook.size();i++){
            if(alBook.get(i).equals( strCurrentID )){
                return alBook.get( i ).toCsvString();
            }
        }
        return "Invalid ID";
    }

    public void updateBook(Book bookToBeUpdated){
        String strID=bookToBeUpdated.strID;
        if (strID.equals( "new" )){
            String newID="1";
            for(int i=0;i<this.alBook.size();i++){
                if(alBook.get(i).getStrID().equals(newID)){

                    newID=Integer.toString( i+2 );
                }else{
                    break;
                }
            }


            bookToBeUpdated.setStrID( newID );
            alBook.add( bookToBeUpdated );
        }else{
            for(int i=0;i<this.alBook.size();i++){
                if(alBook.get( i ).getStrID().equals( strID)){
                    if (bookToBeUpdated.getStrTitle().equals( "" )){  //delete
                        alBook.remove( i );
                        return;
                    }else{
                        alBook.get( i ).update(bookToBeUpdated);
                        return;
                    }


                }
            }
        }
        return;
    }
    public int size(){
        return this.alBook.size();
    }


}
