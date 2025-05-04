package com.example.moyenne;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FailActivity extends AppCompatActivity {

    TextView resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);

        resultat = findViewById(R.id.resultat);

        double moyenne = getIntent().getDoubleExtra("moyenne", 0);

        resultat.setText(" Dommage ! \n\nVous etes récalé !\nVotre Moyenne est :\n" + moyenne);
        resultat.setTextColor(0xFFFF0000);
    }
}
