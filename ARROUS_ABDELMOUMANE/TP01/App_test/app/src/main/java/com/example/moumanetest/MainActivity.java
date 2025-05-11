package com.example.moumanetest;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void moumane(View view){
        EditText editText = (EditText) findViewById(R.id.idtextfeild);
        EditText editText1 = (EditText) findViewById(R.id.editTextTextPassword);
        String email = editText.getText().toString();
        String password =editText1.getText().toString();
        Snackbar.make(view, "Votre email est :"+ email+"\n et password est : "+password, Snackbar.LENGTH_SHORT)
                .setAction("OK", v -> {
                    // Action when clicking "OK"
                }).show();

    }
}