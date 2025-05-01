package com.example.tp2_calculateur_de_moyenne_mallem_wail;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Moyenne extends AppCompatActivity {
    EditText note1, note2, note3;
    Button calculerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moyenne);

        note1 = findViewById(R.id.note1);
        note2 = findViewById(R.id.note2);
        note3 = findViewById(R.id.note3);
        calculerBtn = findViewById(R.id.calculerBtn);

        // Optional: Ensure only numbers are accepted
        note1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        note2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        note3.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        calculerBtn.setOnClickListener(v -> {
            String n1 = note1.getText().toString();
            String n2 = note2.getText().toString();
            String n3 = note3.getText().toString();

            if (n1.isEmpty() || n2.isEmpty() || n3.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            double d1 = Double.parseDouble(n1);
            double d2 = Double.parseDouble(n2);
            double d3 = Double.parseDouble(n3);
            double moyenne = (d1 + d2 + d3) / 3;

            Intent intent = moyenne >= 10 ? new Intent(this, Success.class) : new Intent(this, Fail.class);
            intent.putExtra("MOYENNE", moyenne);
            startActivity(intent);
        });
    }
}
