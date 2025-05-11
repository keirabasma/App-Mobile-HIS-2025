package com.example.moyenne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            findViewById(R.id.button1).setOnClickListener(View ->{
                EditText modul1 = findViewById(R.id.module1);
                float modul1value = Float.parseFloat(modul1.getText().toString());
                EditText modul2 =findViewById(R.id.module2);
                float modul2value = Float.parseFloat(modul2.getText().toString());
                EditText modul3 = findViewById(R.id.module3);
                float modul3value = Float.parseFloat(modul3.getText().toString());
                calculate(modul1value,modul2value,modul3value);
            });
            return insets;
        });
    }

    void calculate(Float module1 ,Float module2,Float module3){
        Float moyenne= (module1+module2+module3)/3;

        if(moyenne>=10){
            Intent succes = new Intent(MainActivity.this,com.example.moyenne.succes.class);
            succes.putExtra("moyenne",moyenne);
            startActivity(succes);
        }else {
            Intent faild = new Intent(MainActivity.this,com.example.moyenne.faild.class);
            faild.putExtra("moyenne",moyenne);
            startActivity(faild);
        }
    }
}