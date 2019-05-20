package com.lambda.readinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class EditBookActivity extends AppCompatActivity {
    EditText etInputName;
    EditText etInputReason;
    CheckBox cbHasRead;
    EditText etID;
    Context context;
    Book bookCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit_book );
        receiveData();
        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // search for drug by brand name
                sendData();
            }
        });
        findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // search for drug by brand name

                sendDatatoCancel();
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // search for drug by brand name

                sendDatatoDelete();
            }
        });

    }
    @Override
    public void onBackPressed() {

        sendData();

    }

    private void receiveData(){
        etInputName=findViewById(R.id.input_name);
        etInputReason=findViewById(R.id.input_reason);
        cbHasRead=findViewById( R.id.hasread );
        etID=findViewById( R.id.input_id );
        context = getApplicationContext();




        String strTemp=(String) getIntent().getStringExtra(  "DATA");
        if(strTemp.equals( "")){
            bookCurrent = new Book( ",,,new" );
            etInputName.setText( "" );
            etInputReason.setText( "" );
            etID.setText( "new" );
            cbHasRead.setChecked( bookCurrent.bHasBeenRead );
        }else {
            bookCurrent = new Book( strTemp );
            etInputName.setText( bookCurrent.getStrTitle() );
            etInputReason.setText( bookCurrent.getStrReasonToRead() );
            etID.setText( bookCurrent.strID );
            cbHasRead.setChecked( bookCurrent.bHasBeenRead );
        }

    }

    private void sendData(){
        bookCurrent.setStrTitle( etInputName.getText().toString());
        bookCurrent.setStrReasonToRead( etInputReason.getText().toString() );
        bookCurrent.bHasBeenRead=cbHasRead.isChecked();
        bookCurrent.setStrID( etID.getText().toString() );
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("DATA", bookCurrent.toCsvString());
        setResult( RESULT_OK,intent );
        onActivityResult( 1, RESULT_OK ,intent);

   //     startActivityForResult(intent, 1);
        finish();

    }

    private void sendDatatoCancel(){
        Intent intent = new Intent(context, MainActivity.class);

        intent.putExtra("DATA", ",,false,"+bookCurrent.getStrID());
        setResult( RESULT_CANCELED,intent );

        onActivityResult( 1, RESULT_CANCELED ,intent);

  //      startActivityForResult(intent, 1);
        finish();
    }

    private void sendDatatoDelete(){
        bookCurrent.setStrTitle("");
        bookCurrent.setStrReasonToRead("" );
        bookCurrent.bHasBeenRead=cbHasRead.isChecked();
        bookCurrent.setStrID( etID.getText().toString() );
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("DATA", bookCurrent.toCsvString());
        setResult( RESULT_OK,intent );
        onActivityResult( 1, RESULT_OK ,intent);

        //     startActivityForResult(intent, 1);
        finish();

    }
}





