package com.example.moyenne;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText note1, note2;
    Button btnCalculer;
    TextView resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        note1 = findViewById(R.id.note1);
        note2 = findViewById(R.id.note2);
        btnCalculer = findViewById(R.id.btnCalculer);
        resultat = findViewById(R.id.resultat);

        btnCalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n1 = note1.getText().toString();
                String n2 = note2.getText().toString();

                if (!n1.isEmpty() && !n2.isEmpty()) {
                    double moyenne = (Double.parseDouble(n1) + Double.parseDouble(n2)) / 2;
                    resultat.setText("Moyenne : " + moyenne);
                } else {
                    resultat.setText("Veuillez entrer les deux notes.");
                }
            }
        });
    }
}
