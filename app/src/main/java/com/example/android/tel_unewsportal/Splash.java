package com.example.android.tel_unewsportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser != null){
            Intent newtask = new Intent(Splash.this, MainActivity.class);
            newtask.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            newtask.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newtask);
        }

        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
    }

    public void login(View view) {
        final String getemail = email.getText().toString();
        final String getpass = password.getText().toString();

        if (getemail.isEmpty()){
            msg("email harus diisi");
            return;
        }
        if (getpass.isEmpty()){
            msg("password harus diisi");
            return;
        }

        if (getemail.equals("admin") && getpass.equals("admin123")){
            Intent login = new Intent(Splash.this, Admin.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(login);
            return;
        }

        user.orderByChild("username").equalTo(getemail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        Log.d("GARIDA", "onDataChange: " + ds.toString());
                        User user = ds.getValue(User.class);

                        assert user != null;
                        if(user.getUsername().equals(getemail)){
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(user.getEmail(), getpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Intent login = new Intent(Splash.this, MainActivity.class);
                                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(login);
                                    }
                                }
                            });
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

    private void msg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

