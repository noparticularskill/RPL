package com.example.android.tel_unewsportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.tel_unewsportal.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    DatabaseReference user = FirebaseDatabase.getInstance().getReference("User");
    EditText email, username, password;
    String mEmail, mUsername, mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser != null){
            Intent newtask = new Intent(Register.this, MainActivity.class);
            newtask.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            newtask.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newtask);
        }

        email=findViewById(R.id.input_email);
        username=findViewById(R.id.input_confirm);
        password=findViewById(R.id.input_password);
    }

    public void register(View view) {
        final String id = user.push().getKey();
        mEmail = email.getText().toString();
        mPassword = password.getText().toString();
        mUsername = username.getText().toString();

        if (mUsername.isEmpty()){
            msg("username harus diisi");
            return;
        }
        if (mEmail.isEmpty()){
            msg("email harus diisi");
            return;
        }
        if (mPassword.isEmpty()){
            msg("password harus diisi");
            return;
        }
        if (mPassword.length() < 6){
            msg("password minimal 6 karakter");
            return;
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User apakek = new User(mEmail, mUsername);
                    user.child(id).setValue(apakek).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent terserah = new Intent(Register.this,Splash.class);
                                startActivity(terserah);
                                finish();
                            }
                        }
                    });

                }else{
                    msg(task.getResult().toString());
                }
            }
        });


    }

    private void msg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
