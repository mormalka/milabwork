package com.example.morm4.ex3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class StarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stark);


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.StarkRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(new String[]{"Eddard (Ned) Stark", "Catelyn (Cat) Tully Stark", "Jon Snow", "Bran Stark", "Sansa Stark", "Arya Stark", "Robb Stark", "Rickon Stark"}));

    }
}
