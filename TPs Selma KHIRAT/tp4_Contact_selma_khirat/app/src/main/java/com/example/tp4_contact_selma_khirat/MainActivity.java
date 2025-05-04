package com.example.tp4_contact_selma_khirat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText nom, prenom, telephone;
    Button btnEnvoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        telephone = findViewById(R.id.telephone);
        btnEnvoyer = findViewById(R.id.btnEnvoyer);

        btnEnvoyer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Contact_list_Activity.class);
            intent.putExtra("nom", nom.getText().toString());
            intent.putExtra("prenom", prenom.getText().toString());
            intent.putExtra("telephone", telephone.getText().toString());
            startActivity(intent);
        });
    }
}
