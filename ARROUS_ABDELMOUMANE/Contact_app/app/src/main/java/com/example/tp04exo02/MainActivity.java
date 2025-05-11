package com.example.tp04exo02;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Persone> personList = new ArrayList<>();
    customAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText inputname = findViewById(R.id.name);
                    String name = inputname.getText().toString();

                    EditText inputlastname = findViewById(R.id.lastname);
                    String lastname = inputlastname.getText().toString();

                    EditText inputphone = findViewById(R.id.phone);
                    String phoneText = inputphone.getText().toString();
                    if(name.isEmpty() || lastname.isEmpty()||phoneText.isEmpty()){
                        Toast.makeText(MainActivity.this, "Veuillez entrer tous les valeurs !", Toast.LENGTH_SHORT).show();

                    }else {
                        addtolist(name, lastname, phoneText);

                    }

                    Log.d("SUBMIT", "Contact added!");

                }
            });
            return insets;
        });


    }


    void addtolist(String name, String Lastname, String phone) {
        // التحقق إذا كان الشخص موجودًا في القائمة أم لا
        boolean exists = false;
        for (Persone p : personList) {
            // البحث باستخدام رقم الهاتف (أو الاسم إذا أردت)
            if (p.Phone.equals(phone)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            // إذا كان الشخص موجودًا بالفعل
            Toast.makeText(MainActivity.this, "الشخص موجود بالفعل في القائمة!", Toast.LENGTH_SHORT).show();
        } else {
            // إذا لم يكن موجودًا، نقوم بإضافته
            Persone p = new Persone();
            p.name = name;
            p.Lastname = Lastname;
            p.Phone = phone;
            personList.add(p);

            // تمرير القائمة إلى ListActivity
            Intent list = new Intent(MainActivity.this, ListActivity.class);
            list.putExtra("personList", personList);  // تمرير القائمة إلى الأنشطة الأخرى
            startActivity(list);
        }
    }


    void removefromlist (Number phone){
        for (int i = 0; i < personList.size(); i++) {
            Persone p = personList.get(i);
            if (p.Phone.equals(phone)) {
                personList.remove(i);
                break;
            }
        }

    }
}