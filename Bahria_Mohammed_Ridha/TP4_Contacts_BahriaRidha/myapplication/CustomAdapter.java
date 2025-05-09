package com.example.reda.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;



    public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Contact> contactlist;
    LayoutInflater inflter;


    public CustomAdapter(Context applicationContext,  ArrayList<Contact> l) {
        this.context = context;
        this.contactlist = l;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return contactlist.size() ;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView( int position,View view, ViewGroup parent) {

        view = inflter.inflate(R.layout.contact_lay, null);
        TextView TvName = (TextView) view.findViewById(R.id.TV_Nom);
        TextView  tvPrenom = (TextView) view.findViewById(R.id.TV_Prenom);
        TvName.setText(contactlist.get(position).getNom());
        tvPrenom.setText(contactlist.get(position).getPrenom());


        return view;
    }

}

