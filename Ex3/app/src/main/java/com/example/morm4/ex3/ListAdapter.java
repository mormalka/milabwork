package com.example.morm4.ex3;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView;

public class ListAdapter extends RecyclerView.Adapter {
    public String[] mDataSet;
    public Drawable[] mImageSet;

    public ListAdapter(String[] data, Drawable[] images) {
        mDataSet = data;
        mImageSet = images;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private View CharView;

        public MyViewHolder(View view) {
            super(view);
            CharView = view;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_char_view_holder, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String textForDisplay = mDataSet[position];
        TextView txt = ((MyViewHolder)holder).CharView.findViewById(R.id.CharName);
        txt.setText(textForDisplay);
        Drawable ToDisplay = mImageSet[position];
        ImageView image = ((MyViewHolder)holder).CharView.findViewById(R.id.CharImage);
        image.setImageDrawable(ToDisplay);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
