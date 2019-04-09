package com.example.android.tel_unewsportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapterberita extends RecyclerView.Adapter<Adapterberita.Myviewholder> {
    List <Modelberita> modelberitaa;

    public Adapterberita(List<Modelberita> modelberitaa) {
        this.modelberitaa = modelberitaa;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Myviewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tampilanberita,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
        Modelberita modelberitaaa = modelberitaa.get(i);
        myviewholder.tampilanevent.setText(modelberitaaa.judul);
        myviewholder.gambar.setImageResource(modelberitaaa.gambar);
        myviewholder.berita.setText(modelberitaaa.berita);
    }

    @Override
    public int getItemCount() {
        return modelberitaa.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView tampilanevent;
        ImageView gambar;
        TextView berita;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tampilanevent = itemView.findViewById(R.id.textView6);
            gambar = itemView.findViewById(R.id.imageView2);
            berita = itemView.findViewById(R.id.textView5);
        }
    }
}
