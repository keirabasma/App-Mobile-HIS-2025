package com.example.tp2_calcmoyenne;

import static android.os.Build.VERSION_CODES.R;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class FailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);

        double moyenne = getIntent().getDoubleExtra("MOYENNE", 0);

        TextView moyenneText = findViewById(R.id.moyenneValue);
        moyenneText.setText("Moyenne: " + moyenne);
    }
}