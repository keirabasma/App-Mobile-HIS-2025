package com.example.tp1_calculatrice_mallemwail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class Calculatrice extends AppCompatActivity {

    EditText number1, number2;

    Button btnAdd, btnSub, btnMul, btnDiv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculatrice);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        btnAdd.setOnClickListener(view -> calculate('+'));
        btnSub.setOnClickListener(view -> calculate('-'));
        btnMul.setOnClickListener(view -> calculate('*'));
        btnDiv.setOnClickListener(view -> calculate('/'));
    }

    private void calculate(char operator) {
        String input1 = number1.getText().toString();
        String input2 = number2.getText().toString();
        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this, "You need to enter both nombres", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(this, "Division by zero impossible !", Toast.LENGTH_SHORT).show();
                    return;
                }
                res = num1 / num2;
                break;
        }

        //take result to the second page
        Intent intent = new Intent(Calculatrice.this, Result.class);
        intent.putExtra("result", String.valueOf(res));
        startActivity(intent);




    }




}