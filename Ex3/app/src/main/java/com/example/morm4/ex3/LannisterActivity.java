package com.example.morm4.ex3;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
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
        String[] CharNames = new String[]{"Tywin Lannister ","Cersei Lannister ", "Jaime Lannister ", "Tyrion Lannister", "Lancel Lannister ", "Joffrey Lannister"};
        Drawable[] CharImages = new Drawable[]{ ContextCompat.getDrawable(this,R.drawable.tywinn),ContextCompat.getDrawable(this,R.drawable.cerseii), ContextCompat.getDrawable(this,R.drawable.jamiee),ContextCompat.getDrawable(this,R.drawable.tyrionn),ContextCompat.getDrawable(this,R.drawable.lancell),ContextCompat.getDrawable(this,R.drawable.joffreyy)};
        recyclerView.setAdapter(new ListAdapter( CharNames, CharImages));
        }
}

