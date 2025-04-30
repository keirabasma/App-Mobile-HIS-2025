package com.example.app;



import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface2);

        double moyenne = getIntent().getDoubleExtra("MOYENNE", 0);

        TextView moyenneText = findViewById(R.id.moyenneValue);
        moyenneText.setText("Moyenne: " + moyenne);
    }
}



