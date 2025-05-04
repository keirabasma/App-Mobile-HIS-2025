package com.example.tp4_contactapp_mallemwail;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ListeContactsActivity extends AppCompatActivity {

    ListView contactsList;
    ArrayAdapter<String> adapter;
    ArrayList<String> contactsArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactsList = findViewById(R.id.list_contacts);

        // Get extras passed from Intent (only one contact info)
        String nom = getIntent().getStringExtra("nom");
        String prenom = getIntent().getStringExtra("prenom");
        String phone = getIntent().getStringExtra("phone");

        if (nom != null && prenom != null && phone != null) {
            String contactInfo = nom + " " + prenom + "\n" + phone;
            contactsArray.add(contactInfo); // Only add the single contact passed from MainActivity
        }

        // Set up the adapter and display the contact
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactsArray);
        contactsList.setAdapter(adapter);
    }
}