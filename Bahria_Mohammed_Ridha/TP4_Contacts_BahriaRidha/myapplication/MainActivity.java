package com.example.reda.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list1=(ListView) findViewById(R.id.simpleListView);
        Contact cc1=new Contact("reda", "bahrai","0656543278");
        Contact cc2=new Contact("reda", "bahria","0698798793");
        Contact cc3=new Contact("nazim", "zidan","0670669623");
        Contact cc4=new Contact("nazim", "zidane","069686280");
        ArrayList<Contact> mylist=new ArrayList<Contact>();
        mylist.add(cc1);
        mylist.add(cc2);
        mylist.add(cc3);
        mylist.add(cc4);

        CustomAdapter customAdapter = new CustomAdapter (getApplicationContext() ,mylist );
        list1.setAdapter (customAdapter);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                         public void onItemClick(AdapterView<?> parent, View view,
                                                                 int position, long id) {

                                             Intent myIntent = new Intent(MainActivity.this, ListItemActivity1.class);
                                             startActivityForResult(myIntent, 0);

                                         }
                                     });
 
    }
