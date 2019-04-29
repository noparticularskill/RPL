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

public class CreateArticle extends AppCompatActivity {
    Button mbtnPostArticle, mAddArticle;
    EditText mtitleArticle, mcontentArticle, mauthorArticle;
    ImageView getImgArticle;
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    byte[] bit;
    private String mantul;
    private static final int REQUEST_GET_SINGLE_FILE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_article);
        getSupportActionBar().hide();

        mbtnPostArticle = findViewById(R.id.btnPostE);
        mtitleArticle = findViewById(R.id.titleArticle);
        mcontentArticle = findViewById(R.id.contentArticle);
        mauthorArticle = findViewById(R.id.authorArticle);
        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance();
        mAddArticle= findViewById(R.id.btnAdd_Event);
        getImgArticle = findViewById(R.id.imageE);

        mAddArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                        REQUEST_GET_SINGLE_FILE);

            }
        });



        mbtnPostArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String title,author, content;

                mantul = "";
                title = mtitleArticle.getText().toString().trim();
                content = mcontentArticle.getText().toString().trim();
                author = mauthorArticle.getText().toString().trim();

                if (title.isEmpty()){
                    pesan("Title harus diisi");
                    return;
                }
                if (author.isEmpty()){
                    pesan("Author harus diisi");
                    return;
                }
                if (content.isEmpty()){
                    pesan("Deskripsi harus diisi");
                    return;
                }

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

                                        DatabaseReference dbnews = mDatabase.getReference("Article").push();
                                        Modelberita mb = new Modelberita(dbnews.getKey(),title,mantul, "Student",content,author, "Belum Lulus Sensor", System.currentTimeMillis());

                                        dbnews.setValue(mb).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(CreateArticle.this, "Posted", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(CreateArticle.this, MainActivity.class));
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
                    DatabaseReference dbnews = mDatabase.getReference("Article").push();
                    Modelberita mb = new Modelberita(dbnews.getKey(),title,"", "Student",content,author, "Belum Lulus Sensor", System.currentTimeMillis());

                    dbnews.setValue(mb).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(CreateArticle.this, "Posted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CreateArticle.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
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
                    getImgArticle.setImageBitmap(imageBitmap);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
                    bit = baos.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private void pesan(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

