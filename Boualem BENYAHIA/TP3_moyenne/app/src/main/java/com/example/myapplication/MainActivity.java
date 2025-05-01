package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText input1, input2, input3;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input1.getText().toString().isEmpty() ||
                    input2.getText().toString().isEmpty() ||
                    input3.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivity.this, "Veuillez entrer une valeur !", Toast.LENGTH_SHORT).show();
                    return;
                }

                double val1 = Double.parseDouble(input1.getText().toString());
                double val2 = Double.parseDouble(input2.getText().toString());
                double val3 = Double.parseDouble(input3.getText().toString());

                double moyenne = calculerMoyenne(val1, val2, val3);

                if(moyenne>=10){
                    Intent intent = new Intent(MainActivity.this, Succes.class);
        intent.putExtra("moyenne", moyenne);
        startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, Faill.class);
        intent.putExtra("moyenne", moyenne);
        startActivity(intent);
                }
            }
        });
    }

    private double calculerMoyenne(double val1, double val2, double val3) {
        return (val1 + val2 + val3) / 3;
    }
}
