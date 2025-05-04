package com.example.tp4_contact_remadhnia_yassine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editNom, editPrenom, editNumero;
    Button btnSoumettre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNom = findViewById(R.id.editNom);
        editPrenom = findViewById(R.id.editPrenom);
        editNumero = findViewById(R.id.editNumero);
        btnSoumettre = findViewById(R.id.btnSoumettre);

        btnSoumettre.setOnClickListener(v -> {
            String nom = editNom.getText().toString();
            String prenom = editPrenom.getText().toString();
            String numero = editNumero.getText().toString();

            Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
            intent.putExtra("nom", nom);
            intent.putExtra("prenom", prenom);
            intent.putExtra("numero", numero);
            startActivity(intent);
        });
    }
}