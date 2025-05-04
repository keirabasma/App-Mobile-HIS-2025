package com.example.tp_contactt;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import android.widget.*;


import androidx.appcompat.app.AppCompatActivity;



    public class MainActivity extends AppCompatActivity {
        EditText Nom, Prenom, Telnum;
        Button Btnsave;
        static ArrayList<String> contactsList = new ArrayList<>();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Nom = findViewById(R.id.nom);
            Prenom = findViewById(R.id.prenom);
            Telnum = findViewById(R.id.telnum);
            Btnsave = findViewById(R.id.btnsave);

            Btnsave.setOnClickListener(v -> {
                String nomStr = Nom.getText().toString();
                String prenomStr = Prenom.getText().toString();
                String telnumStr = Telnum.getText().toString();


                if(nomStr.isEmpty() || prenomStr.isEmpty() || telnumStr.isEmpty()) {
                    Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                contactsList.add(nomStr + "      " + prenomStr + "     " + telnumStr);
                Intent intent = new Intent(MainActivity.this, ListContactActivity.class);
                startActivity(intent);
            });
        }
    }