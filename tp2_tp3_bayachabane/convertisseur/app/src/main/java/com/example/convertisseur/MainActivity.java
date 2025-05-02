package com.example.convertisseur;


import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    RadioButton rbDinarToEuro, rbEuroToDinar;
    TextView result;

    static final int MENU_TAUX_D_E = 1;
    static final int MENU_TAUX_E_D = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_float);
        rbDinarToEuro = findViewById(R.id.radio_dinar_euro);
        rbEuroToDinar = findViewById(R.id.radio_euro_dinar);
        result = findViewById(R.id.result);

        registerForContextMenu(rbDinarToEuro);
        registerForContextMenu(rbEuroToDinar);
    }

    public float dinarsToEuro(float valeurDinar) {
        return (float) (valeurDinar * 0.0071);
    }

    public float euroToDinar(float valeurEuro) {
        return (float) (valeurEuro * 140.45);
    }

    public void convertir(View view) {
        if (editText.getText().toString().isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Erreur")
                    .setMessage("Le champ de saisie est vide.")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        float number = Float.parseFloat(editText.getText().toString());
        float resultValue;

        if (rbDinarToEuro.isChecked()) {
            resultValue = dinarsToEuro(number);
        } else if (rbEuroToDinar.isChecked()) {
            resultValue = euroToDinar(number);
        } else {
            Toast.makeText(this, "Veuillez sélectionner un type de conversion", Toast.LENGTH_SHORT).show();
            return;
        }

        result.setText("Résultat : " + resultValue);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Taux de conversion");
        menu.add(0, MENU_TAUX_D_E, 0, "Taux dinar -> euro");
        menu.add(0, MENU_TAUX_E_D, 0, "Taux euro -> dinar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_TAUX_D_E:
                Toast.makeText(this, "1 Dinar = 0.0071 Euro", Toast.LENGTH_SHORT).show();
                return true;
            case MENU_TAUX_E_D:
                Toast.makeText(this, "1 Euro = 140.45 Dinars", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Conversion Dinar <-> Dollar");
        menu.add("Quitter");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("Quitter")) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
