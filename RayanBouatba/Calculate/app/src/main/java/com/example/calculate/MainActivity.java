package com.example.calculate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        result = findViewById(R.id.result);
    }

    public void onOperationClick(View view) {

        String val1 = num1.getText().toString();
        String val2 = num2.getText().toString();

        if (val1.isEmpty() || val2.isEmpty()) {
            result.setText("Vous n'avez pas entrer deux nombres !");
            return;
        }

        double a = Double.parseDouble(val1);
        double b = Double.parseDouble(val2);
        double res = 0;

        int id = view.getId();

        if (id == R.id.add) {
            res = a + b;
        } else if (id == R.id.sub) {
            res = a - b;
        } else if (id == R.id.mul) {
            res = a * b;
        } else if (id == R.id.div) {
            if (b != 0) {
                res = a / b;
            } else {
                result.setText("Division par zéro impossible");
                return;
            }
        }

        result.setText("Résultat : " + res);
    }
}
