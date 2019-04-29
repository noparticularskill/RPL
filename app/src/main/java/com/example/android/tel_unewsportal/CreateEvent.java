package com.example.android.tel_unewsportal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.tel_unewsportal.Model.ModelEvent;
import com.example.android.tel_unewsportal.Model.Modelberita;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CreateEvent extends AppCompatActivity {
    Button mbtnPostEvent, mAddEvent;
    ImageView getImgEvent;
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    byte[] bit;
    private String mantul;
    private static final int REQUEST_GET_SINGLE_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        getSupportActionBar().hide();

        mbtnPostEvent = findViewById(R.id.btnPostE);
        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance();
        mAddEvent= findViewById(R.id.btnAdd_Event);
        getImgEvent = findViewById(R.id.imageE);

        mAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                        REQUEST_GET_SINGLE_FILE);

            }
        });

        mbtnPostEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bit != null){
                    final StorageReference ref = FirebaseStorage.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()+""+System.currentTimeMillis());
                    ref.putBytes(bit).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        mantul = uri.toString();

                                        DatabaseReference dbnews = mDatabase.getReference("Event").push();
                                        ModelEvent mb = new ModelEvent(dbnews.getKey(),mantul, "Event", "Belum Lulus Sensor", System.currentTimeMillis());

                                        dbnews.setValue(mb).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(CreateEvent.this, "Posted", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(CreateEvent.this, MainActivity.class));
                                                    finish();
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                }else{
                    Toast.makeText(CreateEvent.this, "PTK KAU", Toast.LENGTH_SHORT).show();
                    Toast.makeText(CreateEvent.this, "Silahkan input poster", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_GET_SINGLE_FILE && resultCode == RESULT_OK){
            if (data != null){
                Uri uri = data.getData();
                try {
                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    getImgEvent.setImageBitmap(imageBitmap);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
                    bit = baos.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

}

