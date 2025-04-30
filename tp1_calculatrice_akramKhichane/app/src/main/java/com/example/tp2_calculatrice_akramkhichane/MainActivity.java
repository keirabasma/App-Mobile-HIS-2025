package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

import com.example.tp2_calculatrice_akramkhichane.R;

public class MainActivity extends AppCompatActivity {

    EditText number1, number2;
    TextView result;
    Button btnAdd, btnSub, btnMul, btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        result = findViewById(R.id.result);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        btnAdd.setOnClickListener(v -> calculate('+'));
        btnSub.setOnClickListener(v -> calculate('-'));
        btnMul.setOnClickListener(v -> calculate('*'));
        btnDiv.setOnClickListener(v -> calculate('/'));
    }

    private void calculate(char operator) {
        String str1 = number1.getText().toString();
        String str2 = number2.getText().toString();

        if (str1.isEmpty() || str2.isEmpty()) {
            result.setText("⚠️ Champs vides !");
            return;
        }

        try {
            double num1 = Double.parseDouble(str1);
            double num2 = Double.parseDouble(str2);
            double res = 0;

            switch (operator) {
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num1 - num2;
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        result.setText("⚠️ Division par zéro !");
                        return;
                    } else {
                        res = num1 / num2;
                    }
                    break;
            }

            result.setText("Résultat : " + res);

        } catch (NumberFormatException e) {
            result.setText("⚠️ Entrée invalide !");
        }
    }
}
