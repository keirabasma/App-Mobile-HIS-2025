package com.example.exo5finale14;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Chronometer chronometer;
    private static final String CHANNEL_ID = "alarm_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // Initialize views
        timePicker = findViewById(R.id.timePicker);
        chronometer = findViewById(R.id.chronometer);
        Button startButton = findViewById(R.id.startButton);

        // Setup notification channel
        createNotificationChannel();

        // Set click listener
        startButton.setOnClickListener(v -> {
            try {
                startAlarm();
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Alarm Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel for alarm notifications");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void startAlarm() {
        // Get current time
        Calendar currentTime = Calendar.getInstance();

        // Get selected time from TimePicker (version compatible)
        int hour, minute;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        } else {
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        }

        // Set alarm time
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.set(Calendar.HOUR_OF_DAY, hour);
        alarmTime.set(Calendar.MINUTE, minute);
        alarmTime.set(Calendar.SECOND, 0);
        alarmTime.set(Calendar.MILLISECOND, 0);

        // Adjust if time is in the past
        if (alarmTime.before(currentTime)) {
            alarmTime.add(Calendar.DAY_OF_YEAR, 1);
        }

        // Calculate time difference
        long timeDifference = alarmTime.getTimeInMillis() - currentTime.getTimeInMillis();
        timeDifference = Math.max(timeDifference, 1000); // Minimum 1 second

        // Show remaining time
        int hours = (int) (timeDifference / (1000 * 60 * 60));
        int minutes = (int) ((timeDifference / (1000 * 60)) % 60);
        String message = "Cette alarme se déclenchera dans " + hours + " heures et " + minutes + " minutes";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        // Start countdown
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        chronometer.postDelayed(this::triggerAlarm, timeDifference);
    }

    private void triggerAlarm() {
        // Stop chronometer
        chronometer.stop();

        // Notify user
        Toast.makeText(this, "Le temps est écoulé", Toast.LENGTH_LONG).show();
        vibrateDevice();
        showNotification();
    }

    private void vibrateDevice() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(4000, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(4000);
            }
        }
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Alarme")
                .setContentText("Fin de la durée programmée")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        try {
            manager.notify(1, builder.build());
        } catch (SecurityException e) {
            Toast.makeText(this, "Notification permission required", Toast.LENGTH_SHORT).show();
        }
    }
}