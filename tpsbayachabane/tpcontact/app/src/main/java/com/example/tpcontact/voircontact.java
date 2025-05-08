package com.example.tpcontact;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class voircontact extends Activity {
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.page_liste);

        Bundle b = getIntent().getExtras();
        String n = b.getString("cleNom");
        String p = b.getString("clePrenom");
        String num = b.getString("cleNumero");

        fichecontact fc = new fichecontact(n, p, num);
        ArrayList<fichecontact> mesContacts = new ArrayList<>();
        mesContacts.add(fc);

        ListView laListe = findViewById(R.id.listeContacts);
        adaptateurcontact adapt = new adaptateurcontact(this, R.layout.contact_layout, mesContacts);
        laListe.setAdapter(adapt);
    }
}

