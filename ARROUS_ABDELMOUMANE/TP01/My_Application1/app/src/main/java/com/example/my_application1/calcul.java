package com.example.my_application1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class calcul extends Activity {

    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcul);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.calcul), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public double add(double a, double b){
        return a+b;
    }
    public double sub(double a, double b){
        return a-b;
    }
    public double mul(double a, double b){
        return a*b;
    }
    public double div(double a, double b){
        if(b==0){
            System.out.println("Error: Division by zero!"); // Optional: Display an error message
            return Double.NaN;
        }else{
            return a/b;
        }
    }
    public void displayMessage(View view) {
        // Inflate custom Toast layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Get TextView from the Toast layout
        TextView text = layout.findViewById(R.id.toast_text);

        // Get user inputs
        EditText firstValue = findViewById(R.id.editText1);
        EditText secondValue = findViewById(R.id.editText2);

        String v1Text = firstValue.getText().toString().trim();
        String v2Text = secondValue.getText().toString().trim();

        // Get selected RadioButton
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int rb = radioGroup.getCheckedRadioButtonId();

        // Validation: Check if inputs are empty
        if (v1Text.isEmpty() || v2Text.isEmpty()) {
            text.setText("Saisissez toutes les valeurs!");
        }
        // Validation: Check if a RadioButton is selected
        else if (rb == -1) {
            text.setText("Sélectionnez une opération!");
        }
        else {
            // Convert String to double
            double v1 = Double.parseDouble(v1Text);
            double v2 = Double.parseDouble(v2Text);

            if(rb==R.id.add) {
                 result = add(v1, v2); // Call add() function
            } else if (rb==R.id.sub) {
                 result=sub(v1,v2);
            } else if (rb==R.id.mul) {
                result = mul(v1,v2);
            } else if (rb==R.id.div) {
                result = div(v1,v2);
            }
            // Display result in custom Toast
            text.setText("Résultat: " + result);
        }

        // Show the Toast
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 400);
        toast.show();
    }

}
