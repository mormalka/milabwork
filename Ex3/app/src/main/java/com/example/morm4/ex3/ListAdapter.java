package com.example.morm4.ex3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter {
    private String[] mDataSet;

    public ListAdapter(String[] data) {
        mDataSet = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public MyViewHolder(TextView view) {
            super(view);
            mTextView = view;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        MyViewHolder holder = new MyViewHolder(textView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String textForDisplay = mDataSet[position];
        ((MyViewHolder)holder).mTextView.setText(textForDisplay);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
