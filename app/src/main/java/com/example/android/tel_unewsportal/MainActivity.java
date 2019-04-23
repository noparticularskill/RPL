package com.example.android.tel_unewsportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    List<Modelberita> mList;
    Adapterberita adapterberita;
    RecyclerView rvBerita;

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
        rvBerita.setLayoutManager(new LinearLayoutManager(this));
        rvBerita.setHasFixedSize(true);
        adapterberita = new Adapterberita(mList, this);
        rvBerita.setAdapter(adapterberita);

        for (int i = 0; i<5;i++){
            mList.add(new Modelberita("Design",
                    "https://arbordayblog.org/wp-content/uploads/2018/06/oak-tree-sunset-iStock-477164218-1080x608.jpg",
                    "Design of think","Rl.Stine"));
        }
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
