package com.example.morm4.ex3;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
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
        String[] CharNames = new String[]{"Eddard (Ned) Stark", "Catelyn (Cat) Tully Stark", "Jon Snow", "Bran Stark", "Sansa Stark", "Arya Stark", "Robb Stark"};
        Drawable[] CharImages = new Drawable[]{ContextCompat.getDrawable(this,R.drawable.eddardd),ContextCompat.getDrawable(this,R.drawable.cattullyy),ContextCompat.getDrawable(this,R.drawable.jonsnoww),ContextCompat.getDrawable(this,R.drawable.brann),ContextCompat.getDrawable(this,R.drawable.sansaa),ContextCompat.getDrawable(this,R.drawable.aryaa),ContextCompat.getDrawable(this,R.drawable.robbb)};
        recyclerView.setAdapter(new ListAdapter( CharNames, CharImages));
    }
}
