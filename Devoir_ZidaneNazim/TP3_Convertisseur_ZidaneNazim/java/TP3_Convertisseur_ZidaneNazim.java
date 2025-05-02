package com.example.convertisseur;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputField;
    RadioButton radioDinarToEuro, radioEuroToDinar;
    TextView resultText;
    Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);
        radioDinarToEuro = findViewById(R.id.radioDinarToEuro);
        radioEuroToDinar = findViewById(R.id.radioEuroToDinar);
        resultText = findViewById(R.id.resultText);
        convertButton = findViewById(R.id.convertButton);

        convertButton.setOnClickListener(v -> convert());
    }

    private void convert() {
        String input = inputField.getText().toString();

        if (input.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer une valeur", Toast.LENGTH_SHORT).show();
            return;
        }

        float value = Float.parseFloat(input);
        float result;

        if (radioDinarToEuro.isChecked()) {
            result = value * 0.0071f;
        } else if (radioEuroToDinar.isChecked()) {
            result = value * 140.45f;
        } else {
            Toast.makeText(this, "Sélectionnez une option", Toast.LENGTH_SHORT).show();
            return;
        }

        resultText.setText("Résultat : " + result);
    }
}
