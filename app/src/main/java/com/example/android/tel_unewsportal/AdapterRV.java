package com.example.android.tel_unewsportal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tel_unewsportal.Model.Modelberita;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRV extends RecyclerView.Adapter <AdapterRV.MyViewOlder> {

    List<Modelberita> mList;
    Context context;

    public AdapterRV(List<Modelberita> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewOlder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tampilanberita, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewOlder myViewOlder, int i) {
        final Modelberita item = mList.get(i);
        myViewOlder.tampilanevent.setText(item.judul);
        myViewOlder.berita.setText(item.berita);
        Picasso.get().load(item.gambar).into(myViewOlder.gambar);
        myViewOlder.mcons1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sc = new Intent(context, DetailStudentCorner.class);
                sc.putExtra("julid", item.judul);
                sc.putExtra("aufar", item.author);
                sc.putExtra("hoax", item.berita);
                sc.putExtra("ambar", item.gambar);
                sc.putExtra("anggal", item.mogumogu);
                context.startActivity(sc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewOlder extends RecyclerView.ViewHolder {

        TextView tampilanevent;
        ImageView gambar;
        TextView berita;
        ConstraintLayout mcons1;
        public MyViewOlder(@NonNull View itemView) {
            super(itemView);
            tampilanevent = itemView.findViewById(R.id.textView6);
            gambar = itemView.findViewById(R.id.imageView2);
            berita = itemView.findViewById(R.id.textView5);
            mcons1 = itemView.findViewById(R.id.cons1);
        }
    }
}
