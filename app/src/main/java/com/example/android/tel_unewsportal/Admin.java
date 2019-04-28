package com.example.android.tel_unewsportal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity {

    AdapterAdmin adapterAdmin;
    RecyclerView recyclerView;
    List<Modelberita> modelberitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        modelberitas = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_admin);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterAdmin = new AdapterAdmin(modelberitas, this);
        recyclerView.setAdapter(adapterAdmin);

        setData();
    }

    private void setData() {
        modelberitas.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Student News");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()){
                    Modelberita modelberita = dataSnapshot.getValue(Modelberita.class);
                    if (modelberita != null && modelberita.mogimogi.equals("Belum Lulus Sensor")){
                        modelberitas.add(modelberita);
                        adapterAdmin.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
