package com.example.alarme;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Chronometer chronometer;
    private Button startButton;

    private long durationInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        chronometer = findViewById(R.id.chronometer);
        startButton = findViewById(R.id.startButton);

        timePicker.setIs24HourView(true);

        startButton.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            Calendar alarmTime = Calendar.getInstance();
            alarmTime.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            alarmTime.set(Calendar.MINUTE, timePicker.getMinute());
            alarmTime.set(Calendar.SECOND, 0);

            if (alarmTime.before(now)) {
                alarmTime.add(Calendar.DATE, 1); // pour demain
            }

            durationInMillis = alarmTime.getTimeInMillis() - now.getTimeInMillis();

            long durationMinutes = durationInMillis / 60000;
            long hours = durationMinutes / 60;
            long minutes = durationMinutes % 60;

            Toast.makeText(this, "Cette alarme se déclenchera dans " + hours + " heures et " + minutes + " minutes", Toast.LENGTH_LONG).show();

            // Démarrage du chronomètre
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();

            // Planifier l'alarme
            Intent intent = new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + durationInMillis, pendingIntent);
        });
    }
}

