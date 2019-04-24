package com.example.android.tel_unewsportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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


    }
}
