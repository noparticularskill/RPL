package com.example.android.tel_unewsportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    List<Modelberita> mList;
    List<Modelberita> brtList;
    Adapterberita adapterberita, adapterStudent;
    RecyclerView rvBerita, rvStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mUser == null){
            Intent newtask = new Intent(MainActivity.this, Splash.class);
            newtask.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            newtask.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newtask);
        }

        mList = new ArrayList<>();
        rvBerita = findViewById(R.id.rv_berita);
        rvStudent = findViewById(R.id.recyclerView);
        rvBerita.setLayoutManager(new LinearLayoutManager(this));
        rvBerita.setHasFixedSize(true);
        adapterberita = new Adapterberita(mList, this);
        rvBerita.setAdapter(adapterberita);

        brtList = new ArrayList<>();
        rvStudent.setLayoutManager(new LinearLayoutManager(this, 0, false));
        rvStudent.setHasFixedSize(true);
        adapterStudent = new Adapterberita(brtList, this);
        rvStudent.setAdapter(adapterStudent);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Student News");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Modelberita modelberita = dataSnapshot.getValue(Modelberita.class);
                    mList.add(modelberita);
                    brtList.add(modelberita);
                    adapterberita.notifyDataSetChanged();
                    adapterStudent.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (mUser == null){
            inflater.inflate(R.menu.register, menu);
        }else{
            inflater.inflate(R.menu.logo,menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent newtask = new Intent(MainActivity.this, Splash.class);
        newtask.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        newtask.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        switch (item.getItemId()){
            case R.id.editt:
                startActivity(new Intent(this, CreateNews.class));
                return true;
            case R.id.logout_registerr:
                FirebaseAuth.getInstance().signOut();
                startActivity(newtask);
                return true;
            case R.id.registerr:
                //todo
                return true;
            case R.id.loginn:
                startActivity(newtask);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
