package com.example.morm4.ex3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LannisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lannister);


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.LannisterRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(new String[]{"Tywin Lannister ","Cersei Lannister ", "Jaime Lannister ", "Tyrion Lannister", "Kevan Lannister", "Lancel Lannister "}));

    }
}
