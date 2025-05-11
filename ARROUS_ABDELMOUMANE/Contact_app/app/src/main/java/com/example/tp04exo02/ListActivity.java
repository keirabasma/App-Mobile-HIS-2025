package com.example.tp04exo02;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    customAdapter myadapter;
    ArrayList<Persone> personList; // استلام قائمة الأشخاص من MainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.listactivity); // تأكد من أن layout اسمه list.xml

        // استلام البيانات من الـ Intent
         personList = (ArrayList<Persone>) getIntent().getSerializableExtra("personList");


        // التحقق إذا كانت القائمة فارغة
        if (personList == null) {
            Toast.makeText(this, "لا توجد بيانات لعرضها", Toast.LENGTH_SHORT).show();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listactivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            ListView lv = findViewById(R.id.Vlist);
            myadapter = new customAdapter(personList); // استخدام personList المستلم
            lv.setAdapter(myadapter);
            return insets;
        });
    }
}
