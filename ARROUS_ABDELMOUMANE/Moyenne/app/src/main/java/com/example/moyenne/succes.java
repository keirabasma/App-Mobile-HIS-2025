package com.example.moyenne;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class succes extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.succes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.succes), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            TextView textView = findViewById(R.id.textsucces);
            Float moyenne = getIntent().getFloatExtra("moyenne", 0.0f);
            textView.setText(String.format("Success!%.2f",moyenne));
            return insets;
        });
    }
}
