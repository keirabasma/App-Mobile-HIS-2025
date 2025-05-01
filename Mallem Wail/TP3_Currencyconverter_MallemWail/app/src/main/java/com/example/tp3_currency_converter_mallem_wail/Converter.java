package com.example.tp3_currency_converter_mallem_wail;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class Converter extends AppCompatActivity {

    private EditText editFloat;
    private RadioButton radioDinarToEuro, radioEuroToDinar;
    private TextView textResult;
    private Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter);

        editFloat = findViewById(R.id.edit_float);
        radioDinarToEuro = findViewById(R.id.radio_dinar_to_euro);
        radioEuroToDinar = findViewById(R.id.radio_euro_to_dinar);
        textResult = findViewById(R.id.text_result);
        buttonConvert = findViewById(R.id.button_convert);

        // Hide result initially
        textResult.setVisibility(View.GONE);

        registerForContextMenu(radioDinarToEuro);
        registerForContextMenu(radioEuroToDinar);

        buttonConvert.setOnClickListener(v -> convertir());
    }

    private float dinarsToEuro(float valeurDinar) {
        return (float) (valeurDinar * 0.0071);
    }

    private float euroToDinar(float valeurEuro) {
        return (float) (valeurEuro * 140.45);
    }

    private void convertir() {
        String input = editFloat.getText().toString();

        if (input.isEmpty()) {
            Toast.makeText(this, "Enter a Value", Toast.LENGTH_LONG).show();
            return;
        }

        float value = Float.parseFloat(input);
        float result;

        if (radioDinarToEuro.isChecked()) {
            result = dinarsToEuro(value);
            textResult.setVisibility(View.VISIBLE);
            textResult.setText(String.format("%.4f €", result));
        } else if (radioEuroToDinar.isChecked()) {
            result = euroToDinar(value);
            textResult.setVisibility(View.VISIBLE);
            textResult.setText(String.format("%.2f DA", result));
        } else {
            Toast.makeText(this, "Please select A Conversion", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Taux Dinar -> Euro");
        menu.add(0, v.getId(), 0, "Taux Euro -> Dinar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        if (title.equals("Taux Dinar -> Euro")) {
            Toast.makeText(this, "1 Dinar = 0.0071 Euro", Toast.LENGTH_SHORT).show();
        } else if (title.equals("Taux Euro -> Dinar")) {
            Toast.makeText(this, "1 Euro = 140.45 Dinar", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
