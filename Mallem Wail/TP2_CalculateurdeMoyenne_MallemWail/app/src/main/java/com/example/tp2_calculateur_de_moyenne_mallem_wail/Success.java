package com.example.tp2_calculateur_de_moyenne_mallem_wail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Success extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);

        // Get the "Return to Moyenne" button
        Button returnButton = findViewById(R.id.btnReturn);

        // Set a click listener to navigate back to Moyenne
        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(Success.this, Moyenne.class);
            startActivity(intent);
            finish();  // Optional: finish the current activity so it can't be reached by the back button
        });

        double moyenne = getIntent().getDoubleExtra("MOYENNE", 0);
        TextView moyenneText = findViewById(R.id.moyenneValue);
        moyenneText.setText("votre Moyenne : " + moyenne);
    }
}
