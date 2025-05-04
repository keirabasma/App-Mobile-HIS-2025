package com.example.moyenne;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText note1, note2, note3;
    Button calculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        note1 = findViewById(R.id.note1);
        note2 = findViewById(R.id.note2);
        note3 = findViewById(R.id.note3);
        calculer = findViewById(R.id.calculer);

        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(note1.getText()) || TextUtils.isEmpty(note2.getText()) || TextUtils.isEmpty(note3.getText())) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                } else {
                    double n1 = Double.parseDouble(note1.getText().toString());
                    double n2 = Double.parseDouble(note2.getText().toString());
                    double n3 = Double.parseDouble(note3.getText().toString());

                    double moyenne = (n1 + n2 + n3) / 3;

                    if (moyenne >= 10) {
                        Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                        intent.putExtra("moyenne", moyenne);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, FailActivity.class);
                        intent.putExtra("moyenne", moyenne);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
