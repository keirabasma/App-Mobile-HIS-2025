package com.example.tp3_convertisseur_remadhnia_yassine;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText editEntree;
    private RadioButton radioDinarEuro, radioEuroDinar;
    private TextView textResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEntree = findViewById(R.id.edit_entree);
        radioDinarEuro = findViewById(R.id.radio_dinar_euro);
        radioEuroDinar = findViewById(R.id.radio_euro_dinar);
        textResultat = findViewById(R.id.text_resultat);

        Button btnConvertir = findViewById(R.id.btn_convertir);
        btnConvertir.setOnClickListener(v -> convertir());

        registerForContextMenu(radioDinarEuro);
        registerForContextMenu(radioEuroDinar);

    }

    private void convertir() {
        String input = editEntree.getText().toString().trim();
        if (input.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Please enter a value")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        try {
            float valeur = Float.parseFloat(input);
            float resultat;

            if (radioDinarEuro.isChecked()) {
                resultat = dinarsToEuro(valeur);
            } else {
                resultat = euroToDinar(valeur);
            }

            textResultat.setText(String.valueOf(resultat));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
        }
    }

    private float dinarsToEuro(float valeurDinar) {
        return (float) (valeurDinar * 140.45);
    }

    private float euroToDinar(float valeurEuro) {
        return (float) (valeurEuro * 0.0071);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Taux dinar → euro");
        menu.add(0, 2, 1, "Taux euro → dinar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "1 Dinar = 0.0071 Euro", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Toast.makeText(this, "1 Euro = 140.45 Dinar", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 3, 0, "Conversion Dinar <--> Dollar");
        menu.add(0, 4, 1, "Quitter");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 3:
                Toast.makeText(this, "Conversion Dinar <-> Dollar non implémentée", Toast.LENGTH_SHORT).show();
                return true;
            case 4:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}