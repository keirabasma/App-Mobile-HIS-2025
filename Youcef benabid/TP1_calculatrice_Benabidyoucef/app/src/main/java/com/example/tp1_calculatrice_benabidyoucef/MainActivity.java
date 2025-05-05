package com.example.tp1_calculatrice_benabidyoucef;

import static android.os.Build.VERSION_CODES.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    TextView result;
    Button btnAdd, btnSub, btnMul, btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        result = findViewById(R.id.result);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer('+');
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer('-');
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer('*');
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer('/');
            }
        });
    }

    private void calculer(char op) {
        String val1 = num1.getText().toString();
        String val2 = num2.getText().toString();

        if (val1.isEmpty() || val2.isEmpty()) {
            result.setText("Veuillez remplir les deux champs.");
            return;
        }

        double n1 = Double.parseDouble(val1);
        double n2 = Double.parseDouble(val2);
        double res;

        if (op == '+') {
            res = n1 + n2;
            result.setText("Résultat : " + res);
        } else if (op == '-') {
            res = n1 - n2;
            result.setText("Résultat : " + res);
        } else if (op == '*') {
            res = n1 * n2;
            result.setText("Résultat : " + res);
        } else if (op == '/') {
            if (n2 == 0) {
                result.setText("Erreur : division par zéro.");
            } else {
                res = n1 / n2;
                result.setText("Résultat : " + res);
            }
        }
    };
}