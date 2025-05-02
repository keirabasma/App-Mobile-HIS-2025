package com.example.convertsion;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editMontant;
    private TextView textResultat;
    private RadioButton radioDinar;
    private RadioButton radioEuro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editMontant = findViewById(R.id.edit_float);
        textResultat = findViewById(R.id.txt_resultat);
        radioDinar = findViewById(R.id.radioDinar);
        radioEuro = findViewById(R.id.radioEuro);

        registerForContextMenu(radioDinar);
        registerForContextMenu(radioEuro);
    }

    public void convertir(View view) {
        String saisie = editMontant.getText().toString();

        if (saisie.isEmpty()) {
            afficherAlerte("Erreur", "Veuillez saisir une valeur !");
            return;
        }

        try {
            float montant = Float.parseFloat(saisie);
            float resultat;

            if (radioDinar.isChecked()) {
                resultat = dinarVersEuro(montant);
            } else {
                resultat = euroVersDinar(montant);
            }

            textResultat.setText("Résultat : " + resultat);
        } catch (NumberFormatException e) {
            afficherAlerte("Erreur", "Saisie invalide !");
        }
    }

    private float dinarVersEuro(float montant) {
        return montant * 0.0071f;
    }

    private float euroVersDinar(float montant) {
        return montant * 140.45f;
    }

    private void afficherAlerte(String titre, String message) {
        new AlertDialog.Builder(this)
                .setTitle(titre)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Taux de conversion");
        menu.add(0, 1, 0, "1 Dinar -> 0.0071 Euro");
        menu.add(0, 2, 0, "1 Euro -> 140.45 Dinar");
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
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 3, 0, "Conversion Dinar <-> Dollar");
        menu.add(0, 4, 0, "Quitter");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 3:
                Toast.makeText(this, "Conversion Dinar/Dollar non implémentée", Toast.LENGTH_SHORT).show();
                return true;
            case 4:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
