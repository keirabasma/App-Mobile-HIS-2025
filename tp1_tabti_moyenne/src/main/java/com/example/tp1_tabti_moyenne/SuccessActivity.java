
package com.example.tp1_tabti_moyenne;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SuccessActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        double moyenne = getIntent().getDoubleExtra("MOYENNE", 0);
        TextView result = findViewById(R.id.resultText);
        result.setTextColor(0xFF00FF00); 
        result.setText("Félicitations ! Vous avez réussi !\nVotre Moyenne est: " + moyenne);
    }
}
