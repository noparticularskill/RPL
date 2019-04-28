package com.example.android.tel_unewsportal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterAdmin extends RecyclerView.Adapter<AdapterAdmin.Myviewholder> {
    List<Modelberita> modelberitaa;
    Context context;

    public AdapterAdmin(List<Modelberita> modelberitaa, Context context) {
        this.modelberitaa = modelberitaa;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Myviewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_admin,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder myviewholder, final int i) {
        final Modelberita modelberitaaa = modelberitaa.get(i);

        myviewholder.tvTitle.setText(modelberitaaa.judul);
        myviewholder.tvAuthor.setText(modelberitaaa.author);

        myviewholder.btnAsep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelberita md = new Modelberita(modelberitaaa.uid, modelberitaaa.judul, modelberitaaa.gambar, modelberitaaa.berita, modelberitaaa.author, "Sudah Lulus Sensor", System.currentTimeMillis());
                DatabaseReference a = FirebaseDatabase.getInstance().getReference("Student News").child(modelberitaaa.uid);
                a.setValue(md).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "MANTUL", Toast.LENGTH_SHORT).show();
                        modelberitaa.remove(i);
                        notifyItemRemoved(i);
                        notifyDataSetChanged();
                    }
                });
            }
        });
        myviewholder.btnDelina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelberita md = new Modelberita(modelberitaaa.uid, modelberitaaa.judul, modelberitaaa.gambar, modelberitaaa.berita, modelberitaaa.author, "Tidak Lulus Sensor", System.currentTimeMillis());
                DatabaseReference a = FirebaseDatabase.getInstance().getReference("Student News").child(modelberitaaa.uid);
                a.setValue(md).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "MANTUL", Toast.LENGTH_SHORT).show();
                        modelberitaa.remove(i);
                        notifyItemRemoved(i);
                        notifyDataSetChanged();
                    }
                });
            }
        });
        myviewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailStudentCorner.class);
                i.putExtra("julid", modelberitaaa.judul);
                i.putExtra("aufar", modelberitaaa.author);
                i.putExtra("hoax", modelberitaaa.berita);
                i.putExtra("ambar", modelberitaaa.gambar);
                i.putExtra("anggal", modelberitaaa.mogumogu);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelberitaa.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAuthor;
        Button btnAsep, btnDelina;
        CardView cardView;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            btnAsep = itemView.findViewById(R.id.btn_accept);
            btnDelina = itemView.findViewById(R.id.btn_decline);
            cardView = itemView.findViewById(R.id.cv_admin);
        }
    }
}

