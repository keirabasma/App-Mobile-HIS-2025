package com.example.tpcalcul;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText number1, number2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des éléments de l'interface
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        btnAdd = findViewById(R.id.btn_add);
        btnSub = findViewById(R.id.btn_sub);
        btnMul = findViewById(R.id.btn_mul);
        btnDiv = findViewById(R.id.btn_div);
        answer = findViewById(R.id.answer);

        // Listener commun à tous les boutons
        View.OnClickListener listener = v -> {
            String num1Str = number1.getText().toString().trim();
            String num2Str = number2.getText().toString().trim();

            if (num1Str.isEmpty() || num2Str.isEmpty()) {
                answer.setText("Veuillez remplir les deux champs.");
                return;
            }

            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;
            String operation = "";

            if (v.getId() == R.id.btn_add) {
                result = num1 + num2;
                operation = "+";
            } else if (v.getId() == R.id.btn_sub) {
                result = num1 - num2;
                operation = "-";
            } else if (v.getId() == R.id.btn_mul) {
                result = num1 * num2;
                operation = "*";
            } else if (v.getId() == R.id.btn_div) {
                if (num2 == 0) {
                    answer.setText("Erreur : division par zéro !");
                    return;
                }
                result = num1 / num2;
                operation = "/";
            }

            answer.setText("Résultat : " + num1 + " " + operation + " " + num2 + " = " + result);
        };

        // Associer les listeners
        btnAdd.setOnClickListener(listener);
        btnSub.setOnClickListener(listener);
        btnMul.setOnClickListener(listener);
        btnDiv.setOnClickListener(listener);
    }
}