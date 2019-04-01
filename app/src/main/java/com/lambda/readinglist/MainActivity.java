package com.lambda.readinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Book bookCurrent;
    LinearLayout llScroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        llScroll=findViewById( R.id.scrolling_view );


        //test
        llScroll.addView( buildItemView(new Book( "test,title,comma","to test comma",true,"1"  ) ));
        llScroll.addView( buildItemView(new Book( "test,title,comma,to test comma,true,2")));
        llScroll.addView( buildItemView(new Book("test title","to test",true,"3")));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                receiveData();

            }
            if (resultCode == RESULT_CANCELED) {

            }
        }
    }

        private TextView buildItemView(Book book) {
        TextView tv = new TextView( getApplicationContext() );
        bookCurrent=book;
        tv.setText( book.toCsvString() );

        tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView currentTextView = (TextView)v;
                        sendData(currentTextView);
                    }
        });



        return tv;
    }
    private void sendData(TextView tv){
        Context context = getApplicationContext();


        Intent intent = new Intent(context, EditBookActivity.class);
        intent.putExtra("DATA", tv.getText());
        startActivityForResult(intent, 1);
    }

    private void receiveData(){
        if(bookCurrent==null)return;

        String strTemp=(String) getIntent().getStringExtra("DATA");

        if(strTemp==null)return;
        bookCurrent=new Book(strTemp);
        if (bookCurrent.getStrTitle()=="") {
            bookCurrent.getStrID();

        }else{
            if(bookCurrent.getStrID()=="-1")return;

        }
        return;

    }



}
