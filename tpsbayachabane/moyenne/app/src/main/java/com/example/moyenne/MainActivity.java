package com.example.moyenne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText note1, note2, note3;
    Button btnCalculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        note1 = findViewById(R.id.note1);
        note2 = findViewById(R.id.note2);
        note3 = findViewById(R.id.note3);
        btnCalculer = findViewById(R.id.btnCalculer);

        btnCalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n1 = Integer.parseInt(note1.getText().toString());
                int n2 = Integer.parseInt(note2.getText().toString());
                int n3 = Integer.parseInt(note3.getText().toString());

                int moyenne = (n1 + n2 + n3) / 3;

                if (moyenne >= 10) {
                    Intent i = new Intent(MainActivity.this, reussiActivity.class);
                    i.putExtra("moyenne", moyenne);
                    startActivity(i);
                } else {
                    Intent i = new Intent(MainActivity.this, echecActivity.class);
                    i.putExtra("moyenne", moyenne);
                    startActivity(i);
                }
            }
        });
    }
}
