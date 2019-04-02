package com.lambda.readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            this.preferences = getApplicationContext().getSharedPreferences("Book", MODE_PRIVATE);
            //test
            spd.updateBook( new Book("new", "test,title,comma","to test comma",true  ) );
            llScroll.addView( buildItemView(spd.bkBookByID( spd.getInitialID() ) ));

         //   llScroll.addView( buildItemView(new Book( "test,title,comma,to test comma,true,2")));
           // llScroll.addView( buildItemView(new Book("test title","to test",true,"3")));
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
                Book bkT=spd.bkBookByID( strID );
                for(int i=0;i<spd.size();i++){
                    llScroll.addView(buildItemView( bkT));
                    String strNext=spd.getNextId( bkT.getStrID());
                    if(strNext.equals( "" ))break;
                    bkT=spd.bkBookByID(strNext);
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
        tv.setText( book.toCsvString() );
        writeSharedPreference(book.toCsvString());
        tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView currentTextView = (TextView)v;
                        sendData(currentTextView);
                    }
        });
        return tv;
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
        intent.putExtra("DATA", tv.getText());
        startActivityForResult(intent, 1);
    }
    private void sendEmptyData(){
        Context context = getApplicationContext();


        Intent intent = new Intent(context, EditBookActivity.class);
        intent.putExtra("DATA", "");
        startActivityForResult(intent, 1);
    }

    private void receiveData(Intent intent){
        if(bookCurrent==null)return;

        String strTemp=(String) intent.getStringExtra("DATA");

        if(strTemp==null)return;
        bookCurrent=new Book(strTemp);
        spd.updateBook(bookCurrent);
        return;

    }



}
