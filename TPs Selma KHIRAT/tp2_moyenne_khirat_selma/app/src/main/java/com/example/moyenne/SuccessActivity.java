package com.example.moyenne;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {

    TextView resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        resultat = findViewById(R.id.resultat);

        double moyenne = getIntent().getDoubleExtra("moyenne", 0);

        resultat.setText(" Félicitations ! \n\nVous avez réussi !\nVotre Moyenne est :\n" + moyenne);
        resultat.setTextColor(0xFF00FF00);
    }
}
