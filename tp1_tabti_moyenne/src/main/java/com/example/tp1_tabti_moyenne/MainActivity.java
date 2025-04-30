
package com.example.tp1_tabti_moyenne;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText n1, n2, n3;
    Button calculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

\\ici on recupere fait la laison java xml

        note1 = findViewById(R.id.note1);
        note2 = findViewById(R.id.note2);
        note3 = findViewById(R.id.note3);
        calculer = findViewById(R.id.calculer);

        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(note1.getText()) || TextUtils.isEmpty(note2.getText()) || TextUtils.isEmpty(note3.getText())) {
                    Toast.makeText(MainActivity.this, "Tous les champs doivent être remplis", Toast.LENGTH_SHORT).show();
                    return;
                }
            \\ on fait le casting
                double n1 = Double.parseDouble(note1.getText().toString());
                double n2 = Double.parseDouble(note2.getText().toString());
                double n3 = Double.parseDouble(note3.getText().toString());
                double moyenne = (n1 + n2 + n3) / 3;

                Intent intent;
                if (moyenne > 10) {
                    intent = new Intent(MainActivity.this, SuccessActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, FailActivity.class);
                }
                intent.putExtra("MOYENNE", moyenne);
                startActivity(intent);
            }
        });
    }
}
