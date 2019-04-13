package com.example.android.tel_unewsportal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdapterRV extends RecyclerView.Adapter <AdapterRV.MyViewOlder> {

    List

    @NonNull
    @Override
    public MyViewOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewOlder myViewOlder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewOlder extends RecyclerView.ViewHolder {
        public MyViewOlder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
