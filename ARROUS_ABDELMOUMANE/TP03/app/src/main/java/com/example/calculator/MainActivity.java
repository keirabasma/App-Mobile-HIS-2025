package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText show; // ✅ اجعل show متغيرًا عالميًا داخل الكلاس

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // تخصيص مساحة الحواف
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        show = findViewById(R.id.Show); // ✅ تأكد من التهيئة هنا
        afficher(); // ✅ استدعاء الدالة بعد التهيئة
    }

    public void afficher() {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9,
                R.id.button_plus, R.id.button_min,
                R.id.button_mul, R.id.button_div
        };

        for (int id : buttonIds) {
            Button b = findViewById(id);
            b.setOnClickListener(v -> {
                String current = show.getText().toString(); // ✅ الآن show معرف
                String newText = current + b.getText().toString();
                show.setText(newText);
            });
        }

        Button clearBtn = findViewById(R.id.buttondot);
        clearBtn.setOnClickListener(v -> show.setText(""));

        Button equalBtn = findViewById(R.id.button_eq);
        equalBtn.setOnClickListener(v -> {
            // لاحقًا يمكننا إضافة منطق تقييم العملية
        });
    }
}
