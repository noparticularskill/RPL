package com.example.android.tel_unewsportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    DatabaseReference user = FirebaseDatabase.getInstance().getReference("User");
    EditText email, username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View view) {
        String id = user.push().getKey();
        email=findViewById(R.id.input_email);
        username=findViewById(R.id.input_confirm);
        password=findViewById(R.id.input_password);

        User  apakek = new User(email.getText().toString(), username.getText().toString(), password.getText().toString());
        user.child(id).setValue(apakek);

        Intent terserah = new Intent(Register.this,Splash.class);
        startActivity(terserah);
        finish();
    }
}
