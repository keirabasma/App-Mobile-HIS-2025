package com.example.convertiseur;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertiseur.R;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbDinarToEuro, rbEuroToDinar;
    private EditText etValue;
    private TextView tvResult;
    private Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbDinarToEuro = findViewById(R.id.radioDinarToEuro);
        rbEuroToDinar = findViewById(R.id.radioEuroToDinar);
        etValue        = findViewById(R.id.editValue);
        tvResult       = findViewById(R.id.textResult);
        btnConvert     = findViewById(R.id.buttonConvert);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etValue.getText().toString().trim();
                if (s.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez entrer un nombre", Toast.LENGTH_SHORT).show();
                    return;
                }
                float input = Float.parseFloat(s);
                float output = rbDinarToEuro.isChecked() ? input * 0.0071f : input * 140.45f;
                tvResult.setText("Résultat : " + output);
            }
        });
    }
}