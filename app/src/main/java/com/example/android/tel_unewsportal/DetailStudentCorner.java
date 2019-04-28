package com.example.android.tel_unewsportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailStudentCorner extends AppCompatActivity {

    TextView tvTitle, tvTanggalstudent, tvContributorstudent,tvIsicornerstudent;
    ImageView Imgstudent;
    EditText commentstudent;
    Button Submitstudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_student_corner);

        tvTitle = findViewById(R.id.titlestudent);
        tvTanggalstudent = findViewById(R.id.tanggalstudent);
        tvContributorstudent = findViewById(R.id.contributorstudent);
        tvIsicornerstudent = findViewById(R.id.isicornerstudent);
        Imgstudent = findViewById(R.id.imgstudent);
        commentstudent = findViewById(R.id.komenstudent);
        Submitstudent = findViewById(R.id.submitstudent);

        if (getIntent() != null){
            String a = getIntent().getStringExtra("julid");
            String b = getIntent().getStringExtra("hoax");
            String c = getIntent().getStringExtra("aufar");
            String d = getIntent().getStringExtra("ambar");

            if (a != null) {
                tvTitle.setText(a);
            }
            if (b != null) {
                tvIsicornerstudent.setText(b);
            }
            if (c != null) {
                tvContributorstudent.setText(c);
            }
            if (d != null) {
                Picasso.get().load(d).into(Imgstudent);
            }
        }
    }
}
