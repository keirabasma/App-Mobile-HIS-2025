package com.example.moyenne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText note1, note2, note3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        note1 = findViewById(R.id.note1);
        note2 = findViewById(R.id.note2);
        note3 = findViewById(R.id.note3);
    }

    public void onCalculateClick(View v) {

        String s1 = note1.getText().toString();
        String s2 = note2.getText().toString();
        String s3 = note3.getText().toString();


        if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
            Toast.makeText(this, "Remplissez tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        double n1 = Double.parseDouble(s1);
        double n2 = Double.parseDouble(s2);
        double n3 = Double.parseDouble(s3);

        double moy = (n1 + n2 + n3) / 3;

        Intent intent;
        if (moy > 10) {
            intent = new Intent(this, SuccessActivity.class);
            intent.putExtra("moyenne", moy);
        } else {
            intent = new Intent(this, FailureActivity.class);
        }
        startActivity(intent);
    }
}