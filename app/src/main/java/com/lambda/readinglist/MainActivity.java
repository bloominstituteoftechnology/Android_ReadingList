package com.lambda.readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Book bookCurrent;
    LinearLayout llScroll;
    SharedPreferences preferences;
    SharedPrefsDao spd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        llScroll=findViewById( R.id.scrolling_view );
        spd=new SharedPrefsDao();
        if(this.preferences==null){
            this.preferences = getApplicationContext().getSharedPreferences("BookRecord", MODE_PRIVATE);
            String strRetrieved=preferences.getString(    "IDS_FOR_BOOK"     ,"" );
            if(strRetrieved.equals( "" )){
//test
             //   spd.updateBook( new Book("new", "test,title,comma","to test comma",true  ) );

            }else{
                String[] straTemp=          strRetrieved.split("," );
                for(int i=0;i<straTemp.length;i++){
                    strRetrieved=preferences.getString(    "DATA_FOR_BOOK"+straTemp[i]     ,"" );
                    if(strRetrieved.equals( "" )){
                        setSharedPreferences(straTemp[i],""  );

                       // break;
                    }else{
                        Book bk=new  Book(strRetrieved);

                        llScroll.addView( buildItemView(bk ) );
                    }
                }
            }


         }else{


        }

        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendEmptyData();
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                receiveData(data);
                llScroll.removeAllViews();
                 String strID=spd.getInitialID();
                Book bkTemp=spd.bkBookByID( strID );
                if(bkTemp==null){
                    spd.updateBook( bookCurrent );
                }
                int size=spd.size();
                for(int i=0;i<size;i++){
                    llScroll.addView(buildItemView( bkTemp));
                    String strNext=spd.getNextId( bkTemp.getStrID());

                    if(strNext.equals( "" )){
                        //bkTemp=spd.bkBookByID("new");
                        llScroll.addView(buildItemView( bookCurrent));
                    }else{
                        bkTemp=spd.bkBookByID(strNext);

                    }
                }

            }
            if (resultCode == RESULT_CANCELED) {

            }
        }
    }

    private TextView buildItemView(Book book) {
        if(book==null)return null;
        TextView tv = new TextView( getApplicationContext() );
        bookCurrent=book;
        String strTemp=book.toCsvString();

        SpannableString spannable = new SpannableString(strTemp);
        if(book.isbHasBeenRead()==true) {
            strTemp.replace( "true","" );
            spannable.setSpan(new StrikethroughSpan(), 0, strTemp.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            tv.setText(spannable);
        }else{
            strTemp.replace( "false","" );
            tv.setText(strTemp);

        }
        tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView currentTextView = (TextView)v;
                        sendData(currentTextView);
                    }
        });
        spd.updateBook( book);
        setSharedPreferences(book.strID,strTemp);
        return tv;
    }

    private void setSharedPreferences(String strID,String strContent){
        SharedPreferences.Editor editor = preferences.edit();
        String strRetrieved=preferences.getString(    "IDS_FOR_BOOK"     ,"" );
        if(strRetrieved.contains(strID )){
            if(strContent.equals( "" )){
                strRetrieved.replace( strID,"" );
                strRetrieved.replace(",,", "," );
                editor.putString("IDS_FOR_BOOK", strRetrieved);
                editor.remove( "DATA_FOR_BOOK"+strID);
            }
        }else{
            if(strRetrieved.equals( "" )){
                strRetrieved=strID;
            }else{
                strRetrieved+=","+strID;
            }

            editor.putString("IDS_FOR_BOOK", strRetrieved);
            editor.putString("DATA_FOR_BOOK"+strID, strContent);

        }

        editor.apply();
    }



    private void writeSharedPreference(String str){
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString("string", str);
        editor.apply();
    }
    private String readSharedPreference(String str){
        return  this.preferences.getString("string","");

    }


    private void sendData(TextView tv){
        Context context = getApplicationContext();


        Intent intent = new Intent(context, EditBookActivity.class);
        String strTemp=tv.getText().toString();
        intent.putExtra("DATA", strTemp);
        startActivityForResult(intent, 1);
    }
    private void sendEmptyData(){
        Context context = getApplicationContext();


        Intent intent = new Intent(context, EditBookActivity.class);
        intent.putExtra("DATA", "");
        startActivityForResult(intent, 1);
    }

    private void receiveData(Intent intent){
     //   if(bookCurrent==null)return;

        String strTemp=(String) intent.getStringExtra("DATA");

        if(strTemp==null)return;
        bookCurrent=new Book(strTemp);
        setSharedPreferences( bookCurrent.strID,bookCurrent.toCsvString());

        spd.updateBook(bookCurrent);
        return;

    }



}
