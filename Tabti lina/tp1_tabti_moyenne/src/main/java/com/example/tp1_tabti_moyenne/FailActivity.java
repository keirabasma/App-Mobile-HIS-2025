
package com.example.tp1_tabti_moyenne;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);

        double moyenne = getIntent().getDoubleExtra("MOYENNE", 0);
        TextView result = findViewById(R.id.resultText);
        result.setBackgroundColor(0xFFFF0000); // red
        result.setText("Dommage, vous êtes recalé.\nVotre Moyenne est: " + moyenne);
    }
}
