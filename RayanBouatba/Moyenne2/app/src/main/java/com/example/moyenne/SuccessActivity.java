package com.example.moyenne;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        double moy = getIntent().getDoubleExtra("moyenne", 0);
        TextView tvM = findViewById(R.id.Moyenne);
        tvM.setText("Moyenne : " + moy);
    }
}
