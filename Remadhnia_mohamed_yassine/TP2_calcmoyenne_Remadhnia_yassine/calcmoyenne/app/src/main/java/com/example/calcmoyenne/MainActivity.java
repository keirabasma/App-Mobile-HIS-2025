package com.example.calcmoyenne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;




public class MainActivity extends AppCompatActivity {
    EditText note1,note2,note3;
    Button calculerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        note1 = findViewById(R.id.note1);
        note2 = findViewById(R.id.note2);
        note3 = findViewById(R.id.note3);
        calculerBtn = findViewById(R.id.calculerBtn);

        calculerBtn.setOnClickListener(v -> {
            String num1 = note1.getText().toString();
            String num2 = note2.getText().toString();
            String num3 = note3.getText().toString();
            if(num1.isEmpty()||num2.isEmpty()||num3.isEmpty())
            {
                Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
            else
            {
                double n1 = Double.parseDouble(num1);
                double n2 = Double.parseDouble(num2);
                double n3 = Double.parseDouble(num3);

                double moyenne = (n1+n2+n3)/3;
                Intent intent;
                if (moyenne >= 10) {
                    intent = new Intent(MainActivity.this, SuccessActivity.class);
                    intent.putExtra("MOYENNE", moyenne);
                    startActivity(intent);
                } else {
                    intent = new Intent(MainActivity.this, FailActivity.class);
                    intent.putExtra("MOYENNE", moyenne);
                    startActivity(intent);
                }

            }
        });

    }
}