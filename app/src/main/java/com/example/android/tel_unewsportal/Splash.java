package com.example.android.tel_unewsportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Splash extends AppCompatActivity {
    EditText email;
    EditText password;
    DatabaseReference user = FirebaseDatabase.getInstance().getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void login(View view) {
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);

        String getemail = email.getText().toString();
        String getpass = password.getText().toString();

        Log.d("test",getemail);
        Log.d("test",getpass);

        user.orderByChild("email").equalTo(getemail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){

                        if(ds.child("password").getValue(String.class).equals(password.getText().toString())){
                            Intent login = new Intent(Splash.this, MainActivity.class);
                            startActivity(login);
                        }
                    }
                }else {
                    Log.d("test","not exisr");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void register(View view) {
        Intent register = new Intent(this, Register.class);
        startActivity(register);
    }
}

