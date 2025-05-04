package com.example.conatct;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;


    public class ListContactActivity extends AppCompatActivity {
        ListView listView;
        Button btncreate;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.list_contact);

            listView = findViewById(R.id.listViewContacts);
            btncreate = findViewById(R.id.btncreate);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1,
                    MainActivity.contactsList);

            listView.setAdapter(adapter);

            btncreate.setOnClickListener(v -> {
                Intent intent = new Intent(ListContactActivity.this, MainActivity.class);
                startActivity(intent);
            });
        }
    }




