package com.example.calculatrice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText number1, number2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView answer;

    DecimalFormat formatter = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        btnAdd = findViewById(R.id.btn_add);
        btnSub = findViewById(R.id.btn_sub);
        btnMul = findViewById(R.id.btn_mul);
        btnDiv = findViewById(R.id.btn_div);
        answer = findViewById(R.id.answer);

        btnAdd.setOnClickListener(v -> calculate("+"));
        btnSub.setOnClickListener(v -> calculate("-"));
        btnMul.setOnClickListener(v -> calculate("*"));
        btnDiv.setOnClickListener(v -> calculate("/"));
    }

    private void calculate(String operator) {
        String num1Str = number1.getText().toString().trim();
        String num2Str = number2.getText().toString().trim();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            answer.setText("Veuillez remplir les deux champs.");
            return;
        }

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        answer.setText("Erreur : division par zéro !");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            String formatted = formatter.format(result);
            answer.setText("Résultat : " + num1 + " " + operator + " " + num2 + " = " + formatted);

        } catch (NumberFormatException e) {
            answer.setText("Erreur de saisie. Entrez des nombres valides.");
        }
    }
}
