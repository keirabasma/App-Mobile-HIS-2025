package com.example.moyenne;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class faild extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.faild);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.faild), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            TextView faildtext =findViewById(R.id.textfaild);
            Float moyenne = getIntent().getFloatExtra("moyenne",0.0f);
            faildtext.setText(String.format("we are sorry :%.2f ",moyenne));
            return insets;
        });
    }
}
