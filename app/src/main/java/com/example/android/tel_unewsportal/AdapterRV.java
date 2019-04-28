package com.example.android.tel_unewsportal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tel_unewsportal.Model.ItemModel;

import java.util.List;

public class AdapterRV extends RecyclerView.Adapter <AdapterRV.MyViewOlder> {

    List<ItemModel> mList;

    public AdapterRV(List<ItemModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewOlder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.students_corner_rv, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewOlder myViewOlder, int i) {
        ItemModel item = mList.get(i);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewOlder extends RecyclerView.ViewHolder {

        ImageView imgStud, imgProf;
        TextView tvJudul, tvPenulis, tvCont, tvDate;

        public MyViewOlder(@NonNull View itemView) {
            super(itemView);

            imgStud = itemView.findViewById(R.id.imgStud);
            imgProf = itemView.findViewById(R.id.imgProfpic);
            tvJudul = itemView.findViewById(R.id.txtJudul);
            tvPenulis = itemView.findViewById(R.id.txtPenulis);
            tvCont = itemView.findViewById(R.id.txtContrib);
            tvDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
