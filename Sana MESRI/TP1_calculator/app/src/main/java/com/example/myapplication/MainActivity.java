package com.example.myapplication;
import androidx.core.view.*;
import android.os.Bundle;
import androidx.activity.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.*;
import android.widget.*;
import android.content.*;



public class MainActivity extends AppCompatActivity {

    EditText number1, number2;
    Button btnAdd, btnSub, btnMul, btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        btnAdd.setOnClickListener(view -> calculate('+'));
        btnSub.setOnClickListener(view -> calculate('-'));
        btnMul.setOnClickListener(view -> calculate('*'));
        btnDiv.setOnClickListener(view -> calculate('/'));}

        private void calculate(char operator) {
            String input1 = number1.getText().toString();
            String input2 = number2.getText().toString();
            if (input1.isEmpty() || input2.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer deux nombres", Toast.LENGTH_SHORT).show();
                return;
            }
    
            double num1 = Double.parseDouble(input1);
            double num2 = Double.parseDouble(input2);
            double res = 0;
    
            switch (operator) {
                case '+': res = num1 + num2; break;
                case '-': res = num1 - num2; break;
                case '*': res = num1 * num2; break;
                case '/':
                    if (num2 == 0) {
                        Toast.makeText(this, "Division par zéro !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    res = num1 / num2;
                    break;
            }
    
            // Passer le résultat à la deuxième activité
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            intent.putExtra("resultat", String.valueOf(res));
            startActivity(intent);
           
    
    
    
        }
}