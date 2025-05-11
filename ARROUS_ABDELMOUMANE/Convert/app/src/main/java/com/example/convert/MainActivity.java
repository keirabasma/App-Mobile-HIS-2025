package com.example.convert;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Button button1 = findViewById(R.id.button1);
            Button button3 = findViewById(R.id.button3);
            Button changeButton = findViewById(R.id.button2);
            Button calculate = findViewById(R.id.calculate);
            calculate.setOnClickListener(View -> {
                calculatechange();
            });


            changeButton.setOnClickListener(view -> {
                // نحفظ النص مؤقتًا
                String temp = button1.getText().toString();

                // التبديل بين النصوص
                button1.setText(button3.getText().toString());
                button3.setText(temp);
            });

            return insets;
        });
    }
    private void calculatechange() {
        EditText editText = findViewById(R.id.edit_text);
        TextView resultText = findViewById(R.id.edit_text);
        Button button1 = findViewById(R.id.button1); // From currency
        Button button3 = findViewById(R.id.button3); // To currency

        String inputStr = editText.getText().toString();

        if (inputStr.isEmpty()) {
            resultText.setText("يرجى إدخال قيمة");
            return;
        }

        double inputValue = Double.parseDouble(inputStr);
        double result = 0.0;

        String from = button1.getText().toString().toLowerCase();
        String to = button3.getText().toString().toLowerCase();

         // 1 Dinar = 0.0068 Euro
        double rateEuroToDinar = 250;
        double rateDinarToEuro = 1 / rateEuroToDinar;

        if (from.equals("dinnar") && to.equals("euroo")) {
            result = inputValue * rateDinarToEuro;
        } else if (from.equals("euroo") && to.equals("dinnar")) {
            result = inputValue * rateEuroToDinar;
        } else {
            resultText.setText("تحويل غير معروف");
            return;
        }

        // عرض النتيجة
        resultText.setText(String.format("%.2f", result));

    }

}