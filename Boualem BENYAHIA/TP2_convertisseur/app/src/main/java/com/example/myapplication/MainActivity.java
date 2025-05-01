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

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private RadioButton radioDAtoEU, radioEUtoDA;
    private TextView result;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        radioDAtoEU = findViewById(R.id.da_to_eu);
        radioEUtoDA = findViewById(R.id.eu_to_da);
        result = findViewById(R.id.result);
        button = findViewById(R.id.convert);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertir();
            }
        });
    }

    private float dinarToEuro(float value) {
        return value * 0.0071f;
    }

    private float euroToDinar(float value) {
        return value / 0.0071f;
    }

    private void convertir() {
        String inputValue = input.getText().toString().trim();
        if (inputValue.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer une valeur !", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float value = Float.parseFloat(inputValue);
            float resultat;

            if (radioDAtoEU.isChecked()) {
                resultat = dinarToEuro(value);
                result.setText(String.format("%.2f Euro", resultat));
            } else if (radioEUtoDA.isChecked()) {
                resultat = euroToDinar(value);
                result.setText(String.format("%.2f Dinar", resultat));
            } else {
                Toast.makeText(this, "Veuillez sélectionner une conversion.", Toast.LENGTH_SHORT).show();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valeur invalide !", Toast.LENGTH_SHORT).show();
        }
    }
}
