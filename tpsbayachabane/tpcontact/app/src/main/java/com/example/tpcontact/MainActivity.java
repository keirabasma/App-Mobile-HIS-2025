package com.example.tpcontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    EditText champNom, champPrenom, champNum;

    protected void onCreate(Bundle sauve) {
        super.onCreate(sauve);
        setContentView(R.layout.activity_main);

        champNom = findViewById(R.id.zoneNom);
        champPrenom = findViewById(R.id.zonePrenom);
        champNum = findViewById(R.id.zoneTel);
    }

    public void envoyer(View v) {
        String n = champNom.getText().toString();
        String p = champPrenom.getText().toString();
        String num = champNum.getText().toString();

        Intent i = new Intent(this, voircontact.class);
        i.putExtra("cleNom", n);
        i.putExtra("clePrenom", p);
        i.putExtra("cleNumero", num);
        startActivity(i);
    }
}
