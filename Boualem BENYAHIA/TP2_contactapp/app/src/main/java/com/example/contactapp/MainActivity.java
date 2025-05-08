package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editNom, editPrenom, editPhone;
    Button btnEnvoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNom = findViewById(R.id.edit_nom);
        editPrenom = findViewById(R.id.edit_prenom);
        editPhone = findViewById(R.id.edit_phone);
        btnEnvoyer = findViewById(R.id.button_submit);

        btnEnvoyer.setOnClickListener(v -> {
            String nom = editNom.getText().toString().trim();
            String prenom = editPrenom.getText().toString().trim();
            String phone = editPhone.getText().toString().trim();

            if (nom.isEmpty() || prenom.isEmpty() || phone.isEmpty()) {
                // Show a toast message if any field is empty
                Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, ListeContactsActivity.class);
                intent.putExtra("nom", nom);
                intent.putExtra("prenom", prenom);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Clear the fields when coming back
        editNom.setText("");
        editPrenom.setText("");
        editPhone.setText("");
    }
}
