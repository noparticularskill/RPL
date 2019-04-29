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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tel_unewsportal.Model.ModelEvent;
import com.example.android.tel_unewsportal.Model.Modelberita;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    List<Modelberita> brtList;
    List<ModelEvent> evList;
    List<Modelberita> artList;
    Adapterberita adapterberita;
    AdapterArtikel adapterStudent;
    RecyclerView rvBerita, rvStudent;
    TextView whtsnew, studcorner, events;
    ImageView imgEvent;
    Intent in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mUser == null) {
            Intent newtask = new Intent(MainActivity.this, Splash.class);
            newtask.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            newtask.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newtask);
        }

        whtsnew = findViewById(R.id.whatsnew);
        studcorner = findViewById(R.id.studentscorner);
        events = findViewById(R.id.txtEvent);
        imgEvent = findViewById(R.id.img_event);

        rvBerita = findViewById(R.id.rv_berita);
        rvStudent = findViewById(R.id.recyclerView);

        artList = new ArrayList<>();
        brtList = new ArrayList<>();
        evList = new ArrayList<>();

        rvBerita.setLayoutManager(new LinearLayoutManager(this));
        adapterberita = new Adapterberita(brtList, this);
        rvBerita.setAdapter(adapterberita);

        in = new Intent(MainActivity.this, NewsWall.class);

        rvStudent.setLayoutManager(new LinearLayoutManager(this, 0, true));
        adapterStudent = new AdapterArtikel(artList, this);
        rvStudent.setAdapter(adapterStudent);

        whtsnew.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        in.putExtra("typ","news");
                        startActivity(in);
                    }
                });

        studcorner.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        in.putExtra("typ","student");

                        startActivity(in);
                    }
                }
        );

        events.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        in.putExtra("typ", "event");

                        startActivity(in);
                    }
                }
        );
        
        imgEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "anj", Toast.LENGTH_SHORT).show();
            }
        });

        fetchDataNews();
        fetchDataArticle();
        fetctDateEvent();
    }

    private void fetchDataNews() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("/News");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()){
                    Modelberita modelberita = dataSnapshot.getValue(Modelberita.class);
                    if (modelberita != null){
                        if (modelberita.mogimogi != null){
                            if (modelberita.mogimogi.equals("Sudah Lulus Sensor")){
                                brtList.add(modelberita);
                                adapterberita.notifyDataSetChanged();
                            }
                        }
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

    private void fetctDateEvent() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("/Event");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()){
                    ModelEvent modelberita = dataSnapshot.getValue(ModelEvent.class);
                    if (modelberita != null){
                        if (modelberita.mogimogi != null){

                                if (!modelberita.gambar.isEmpty()) {
                                    Picasso.get().load(modelberita.gambar).into(imgEvent);
                                }

                        }
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

    private void fetchDataArticle() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("/Article");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()){
                    Modelberita modelberita = dataSnapshot.getValue(Modelberita.class);
                    if (modelberita != null){
                        if (modelberita.mogimogi != null){
                            if (modelberita.mogimogi.equals("Sudah Lulus Sensor")){
                                artList.add(modelberita);
                                adapterStudent.notifyDataSetChanged();
                            }
                        }
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
            case R.id.news:
                startActivity(new Intent(this, CreateNews.class));
                return true;
            case R.id.aboutus:
                startActivity(new Intent(this, AboutUs.class));
                return true;
            case R.id.berita:
                startActivity(new Intent(this, CreateArticle.class));
                return true;
            case R.id.event:
                startActivity(new Intent(this, CreateEvent.class));
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
