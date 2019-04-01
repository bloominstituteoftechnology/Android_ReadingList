package com.lambda.readinglist;

public class Book {
   String strTitle;
    String strReasonToRead;
    boolean bHasBeenRead;
    String strID;
    public Book(String strTitle,
            String strReasonToRead,
            boolean bHasBeenRead,
            String strID){
        this.strTitle=strTitle;
        this.strReasonToRead=strReasonToRead;
        this.bHasBeenRead= bHasBeenRead;
        this.strID=strID;
    }

    public Book(String strCSV) {
        String[] strTemp = strCSV.split( "," );
        int length=strTemp.length;
        if (length == 4){

            this.strTitle = strTemp[0].replace("，",",");
            this.strReasonToRead = strTemp[1].replace("，",",");
        //     try {
            this.bHasBeenRead = Boolean.parseBoolean( strTemp[2] );
        //     }catch (BootstrapMethodError e){
//e.printStackTrace();
        //    }
            this.strID = strTemp[3];
        }else {
            for(int i=0;i<length-3;i++) {
                this.strTitle += strTemp[i];
            }

            this.strReasonToRead = strTemp[length-3];
            //     try {
            this.bHasBeenRead = Boolean.parseBoolean( strTemp[length-2] );
            //     }catch (BootstrapMethodError e){
//e.printStackTrace();
            //    }
            this.strID = strTemp[length-1];
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

                    "%s,%s,%b,%s",
        this.strTitle.replace( ",","，" ), this.strReasonToRead.replace( ",","，" ), this.bHasBeenRead,this.strID

            );


    }
}
