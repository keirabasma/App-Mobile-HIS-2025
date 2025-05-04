package com.example.tp4_contact_remadhnia_yassine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private ArrayList<Contact> contactList;
    private LayoutInflater inflater;
    private int resourceLayout;

    public ContactAdapter(Context context, int resource, ArrayList<Contact> list) {
        super(context, resource, list);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        resourceLayout = resource;
        contactList = list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(resourceLayout, null);
        }

        TextView Nom = view.findViewById(R.id.TV_N);
        TextView Prenom = view.findViewById(R.id.TV_P);
        TextView Tel = view.findViewById(R.id.TV_tel);


        Contact currentContact = contactList.get(position);

        Nom.setText(currentContact.getNom());
        Prenom.setText(currentContact.getPrenom());
        Tel.setText(currentContact.getNumero());

        return view;
    }
}
