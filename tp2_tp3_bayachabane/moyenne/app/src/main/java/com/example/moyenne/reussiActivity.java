package com.example.moyenne;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class reussiActivity extends AppCompatActivity {

    TextView textMoyenneReussi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reussi);

        textMoyenneReussi = findViewById(R.id.textMoyenneReussi);

        int moyenne = getIntent().getIntExtra("moyenne", 0);
        textMoyenneReussi.setText("Votre moyenne est : " + moyenne);
    }
}
