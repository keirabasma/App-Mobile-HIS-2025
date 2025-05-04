package com.example.tp4_contact_remadhnia_yassine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    public static ArrayList<Contact> contactList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Intent intent = getIntent();
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        String tel = intent.getStringExtra("numero");

        Contact contact = new Contact(nom, prenom, tel);
        contactList.add(contact);

        ListView listView = findViewById(R.id.Contacts);
        ContactAdapter adapter = new ContactAdapter(this, R.layout.contact_lay, contactList);
        listView.setAdapter((ListAdapter) adapter);
    }
}
