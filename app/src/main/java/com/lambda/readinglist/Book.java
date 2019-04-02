package com.lambda.readinglist;

public class Book {
   String strTitle;
    String strReasonToRead;
    boolean bHasBeenRead;
    String strID;
    public Book(String strID, String strTitle,
            String strReasonToRead,
            boolean bHasBeenRead ){
        this.strTitle=strTitle;
        this.strReasonToRead=strReasonToRead;
        this.bHasBeenRead= bHasBeenRead;
        this.strID=strID;
    }

    public Book(String strCSV) {
        String[] strTemp = strCSV.split( "," );
        int length=strTemp.length;
        if (length <= 4){
            this.strID = strTemp[0];
            this.strTitle = strTemp[1].replace("，",",");
            this.strReasonToRead = strTemp[2].replace("，",",");
        //     try {
            this.bHasBeenRead = Boolean.parseBoolean( strTemp[3] );
        //     }catch (BootstrapMethodError e){
//e.printStackTrace();
        //    }

        }else {
            this.strID = strTemp[0];
            for(int i=1;i<length-2;i++) {
                this.strTitle += strTemp[i];
            }

            this.strReasonToRead = strTemp[length-2];
            //     try {
            this.bHasBeenRead = Boolean.parseBoolean( strTemp[length-1] );
            //     }catch (BootstrapMethodError e){
//e.printStackTrace();
            //    }

        }
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public String getStrReasonToRead() {
        return strReasonToRead;
    }

    public void setStrReasonToRead(String strReasonToRead) {
        this.strReasonToRead = strReasonToRead;
    }

    public boolean isbHasBeenRead() {
        return bHasBeenRead;
    }

    public void setbHasBeenRead(boolean bHasBeenRead) {
        this.bHasBeenRead = bHasBeenRead;
    }

    public String getStrID() {
        return strID;
    }

    public void setStrID(String strID) {
        this.strID = strID;
    }

    public String toCsvString(){


            return String.format(

                    "%s,%s,%s,%b"
                    ,this.strID,this.strTitle.replace( ",","，" ), this.strReasonToRead.replace( ",","，" ), this.bHasBeenRead

            );


    }
    public void update(Book bookToBeUpdated){

        this.strTitle=bookToBeUpdated.strTitle;
        this.strReasonToRead=bookToBeUpdated.strReasonToRead;
        this.bHasBeenRead= bookToBeUpdated.bHasBeenRead;
        this.strID=bookToBeUpdated.strID;
    }
}
