package com.example.android.tel_unewsportal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNews extends AppCompatActivity {
    Button mbutonAdd;
    EditText mtitle, mcontent, mauthor;
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_news);
        mbutonAdd = findViewById(R.id.button);
        mtitle = findViewById(R.id.et_title);
        mcontent = findViewById(R.id.et_content);
        mauthor = findViewById(R.id.et_author);
        mAuth = FirebaseAuth.getInstance();

        mbutonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title,author, content;

                title = mtitle.getText().toString().trim();
                content = mcontent.getText().toString().trim();
                author = mauthor.getText().toString().trim();



            }
        });


    }
}
