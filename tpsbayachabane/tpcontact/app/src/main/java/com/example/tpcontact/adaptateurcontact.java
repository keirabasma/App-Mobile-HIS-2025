package com.example.tpcontact;
import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class adaptateurcontact extends ArrayAdapter<fichecontact> {
    ArrayList<fichecontact> listeContacts;
    LayoutInflater gonfleur;
    int ressource;

    public adaptateurcontact(Context c, int r, ArrayList<fichecontact> lst) {
        super(c, r, lst);
        listeContacts = lst;
        ressource = r;
        gonfleur = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int pos, View v, ViewGroup parent) {
        if (v == null) v = gonfleur.inflate(ressource, null);

        TextView tvNom = v.findViewById(R.id.TV_nom);
        TextView tvPrenom = v.findViewById(R.id.TV_prenom);
        TextView tvNumero = v.findViewById(R.id.TV_num);

        fichecontact fc = listeContacts.get(pos);
        tvNom.setText(fc.getNom());
        tvPrenom.setText(fc.getPrenom());
        tvNumero.setText(fc.getNumero());

        return v;
    }
}

