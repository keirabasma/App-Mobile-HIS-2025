package com.example.calculat;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText show;
    private String currentInput = "";  // متغير لتخزين المدخلات الحالية
    private boolean isOperationClicked = false;  // علم للتحقق إذا تم الضغط على زر عملية
    private boolean isOpenBracketNext = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            show = findViewById(R.id.Show);  // ربط الـ EditText
            show.requestFocus();
            // تعيين OnClickListener للأزرار
            findViewById(R.id.button0).setOnClickListener(view -> appendToInput("0"));
            findViewById(R.id.button1).setOnClickListener(view -> appendToInput("1"));
            findViewById(R.id.button2).setOnClickListener(view -> appendToInput("2"));
            findViewById(R.id.button3).setOnClickListener(view -> appendToInput("3"));
            findViewById(R.id.button4).setOnClickListener(view -> appendToInput("4"));
            findViewById(R.id.button5).setOnClickListener(view -> appendToInput("5"));
            findViewById(R.id.button6).setOnClickListener(view -> appendToInput("6"));
            findViewById(R.id.button7).setOnClickListener(view -> appendToInput("7"));
            findViewById(R.id.button8).setOnClickListener(view -> appendToInput("8"));
            findViewById(R.id.button9).setOnClickListener(view -> appendToInput("9"));
            findViewById(R.id.buttondot).setOnClickListener(view -> appendToInput("."));

            findViewById(R.id.buttonAC).setOnClickListener(view -> clearInput());
            findViewById(R.id.button_eq).setOnClickListener(view -> {
                String input = show.getText().toString(); // جلب النص من EditText
                calculateResult(input);
            });
            findViewById(R.id.button_plus).setOnClickListener(view -> appendToInput("+"));
            findViewById(R.id.button_min).setOnClickListener(view -> appendToInput("-"));
            findViewById(R.id.button_mul).setOnClickListener(view -> appendToInput("*"));
            findViewById(R.id.button_div).setOnClickListener(view -> appendToInput("/"));
            findViewById(R.id.button_delete).setOnClickListener(view -> {deleteLastCharacter(); });
            findViewById(R.id.buttonbrakets).setOnClickListener(view -> {
                if (isOpenBracketNext) {
                    appendToInput("(");
                } else {
                    appendToInput(")");
                }
                isOpenBracketNext = !isOpenBracketNext;  // قلب الحالة
            });

            return insets;
        });
    }

    // هذه الدالة تضيف القيمة إلى المدخلات الحالية
    private void appendToInput(String value) {
        if (isOperationClicked && "0123456789.".contains(value)) {
            currentInput = "";  // إعادة تعيين المدخلات بعد الضغط على عملية
            isOperationClicked = false;
        }
        currentInput += value;
        show.setText(currentInput);  // عرض المدخلات في الـ EditText

        // تحديد المؤشر في نهاية النص
        show.setSelection(show.getText().length());
    }


    // هذه الدالة لحساب النتيجة
    private void calculateResult(String currentInput) {
        try {
            System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            // إذا كان المدخل فارغًا لا نحتاج إلى إجراء الحساب
            if (currentInput.isEmpty()) {
                System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                show.setText("");
                return;
            }

            // تحويل المدخل إلى نتيجة باستخدام الخوارزمية الخاصة
            double result = evaluateExpression(currentInput);
            System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            show.setText(String.valueOf(result));

            // تعيين النتيجة كمدخلات جديدة للحسابات المستقبلية
            currentInput = String.valueOf(result);
        } catch (Exception e) {
            // في حال حدوث خطأ
            show.setText("Error");
            currentInput = "";
        }
    }

    // هذه الدالة لتنفيذ العمليات الحسابية يدويًا
    private double evaluateExpression(String expression) throws Exception {
        net.objecthunter.exp4j.Expression exp = new net.objecthunter.exp4j.ExpressionBuilder(expression).build();
        return exp.evaluate();
    }



    // هذه الدالة لتصفير المدخلات
    private void clearInput() {
        currentInput = "";
        show.setText("");
    }
    // هذه الدالة لحذف آخر حرف من المدخلات
    private void deleteLastCharacter() {
        if (currentInput.length() > 0) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);  // حذف آخر حرف
            show.setText(currentInput);  // تحديث الـ EditText لعرض المدخلات المعدلة
        }
    }

}
