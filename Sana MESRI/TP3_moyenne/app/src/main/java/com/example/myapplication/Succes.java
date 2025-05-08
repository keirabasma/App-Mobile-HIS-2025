package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;
public class Succes extends AppCompatActivity {

    private TextView moyenneResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.succes); // Make sure you have a file named succes.xml in res/layout

        moyenneResult = findViewById(R.id.moyenneResult);

        double moyenne = getIntent().getDoubleExtra("moyenne", 0);
        String message = String.format("Mabrouk .. moyenne : %.2f", moyenne);
        moyenneResult.setText(message);
    }
}
