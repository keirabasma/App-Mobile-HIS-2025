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
public class Faill extends AppCompatActivity {

    private TextView moyenneResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faill);
    
        moyenneResult = findViewById(R.id.moyenneResult);
    
        double moyenne = getIntent().getDoubleExtra("moyenne", 0);
        moyenneResult.setText(String.format("Dommage .. moyenne : %.2f", moyenne));}
    }
    
