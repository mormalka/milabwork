package com.example.morm4.ex3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button StarkButton = (Button)findViewById(R.id.pushButton1);
        StarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StarkActivity.class);
                startActivity(intent);

            }
        });

        Button LannisterButton = (Button)findViewById(R.id.pushButton2);
        LannisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LannisterActivity.class);
                startActivity(intent);

            }
        });
    }
}

