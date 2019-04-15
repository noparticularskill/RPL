package com.example.android.tel_unewsportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentsCorner extends AppCompatActivity {

    RecyclerView rv_stud;
    List<ItemModel> mList;
    AdapterRV adapterRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_corner);

        rv_stud = findViewById(R.id.rv_studentscorner);

        mList = new ArrayList<>();

        rv_stud.setLayoutManager(new GridLayoutManager(this, 2));
        adapterRV = new AdapterRV(mList);
        rv_stud.setAdapter(adapterRV);

        for (int i = 0; i < 4; i++) {
            mList.add(new ItemModel(R.drawable.nas, R.drawable.nas, "Tips & Trick", "Inas Muthia", "Contributor", "Sabtu, 13/04/2019"));
        }
    }
}
