package com.example.morm4.stockvalue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String stockType;
    Button sendButt;
    EditText stockID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stockID= (EditText)findViewById(R.id.editText);
        sendButt= (Button)findViewById(R.id.buttonSend);

        sendButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stockType = stockID.getText().toString();

            }

        });


    }
}
