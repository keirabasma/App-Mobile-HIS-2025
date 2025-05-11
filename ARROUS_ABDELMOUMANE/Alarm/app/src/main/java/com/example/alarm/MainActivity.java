package com.example.alarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private NumberPicker hoursPicker, minutePicker, secondPicker;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hoursPicker=findViewById(R.id.hoursPicker);
        minutePicker = findViewById(R.id.minutePicker);
        secondPicker = findViewById(R.id.secondPicker);
        startButton = findViewById(R.id.startButton);

        hoursPicker.setValue(0);
        minutePicker.setValue(0);
        secondPicker.setValue(0);


        hoursPicker.setMinValue(0);
        hoursPicker.setMaxValue(24);

        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);

        secondPicker.setMinValue(0);
        secondPicker.setMaxValue(59);

        startButton.setOnClickListener(v -> {
            // الحصول على القيم من الـ NumberPicker
            int hours= hoursPicker.getValue();
            int minutes = minutePicker.getValue();
            int seconds = secondPicker.getValue();
            Toast.makeText(
                    MainActivity.this,
                    String.format(
                            Locale.FRENCH,
                            "Cette alarme se déclenchera dans %d heures, %d minutes et %d secondes",
                            hours,                // عدد الساعات
                            minutes,              // عدد الدقائق
                            seconds               // عدد الثواني
                    ),
                    Toast.LENGTH_SHORT
            ).show();

            int totalTimeInSeconds = (hours * 60 * 60)+(minutes * 60) + seconds;

            if (totalTimeInSeconds > 0) {
                startTimer(totalTimeInSeconds);
            } else {
                Toast.makeText(MainActivity.this, "Please select a valid time!", Toast.LENGTH_SHORT).show();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
    }



    private void startTimer(int totalTimeInSeconds) {
        new Handler().postDelayed(() -> {
            // عرض إشعار بدل Toast
            showNotification("المنبّه", "انتهى الوقت!");

            // الاهتزاز لمدة دقيقة
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    long[] pattern = new long[60]; // 60 هزّة = 1 دقيقة (500ms اهتزاز + 500ms سكون)
                    for (int i = 0; i < pattern.length; i++) {
                        pattern[i] = 500;
                    }
                    VibrationEffect effect = VibrationEffect.createWaveform(pattern, -1);
                    vibrator.vibrate(effect);
                } else {
                    vibrator.vibrate(60000); // للأجهزة القديمة
                }
            }
        }, totalTimeInSeconds * 1000);
    }

    private void showNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "alarm_channel";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Alarm Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Channel for alarm notifications");
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                // ضع أيقونة مناسبة داخل res/drawable
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        notificationManager.notify(1, builder.build());
    }



}



