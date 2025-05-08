package com.example.tp5_alarm_mallemwail;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Chronometer chronometer;
    private Button startButton;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        chronometer = findViewById(R.id.chronometer);
        startButton = findViewById(R.id.startButton);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        startButton.setOnClickListener(v -> {
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            Calendar now = Calendar.getInstance();
            Calendar alarmTime = Calendar.getInstance();
            alarmTime.set(Calendar.HOUR_OF_DAY, hour);
            alarmTime.set(Calendar.MINUTE, minute);
            alarmTime.set(Calendar.SECOND, 0);

            if (alarmTime.before(now)) {
                alarmTime.add(Calendar.DAY_OF_MONTH, 1);
            }

            long diffMillis = alarmTime.getTimeInMillis() - now.getTimeInMillis();
            long diffMinutes = (diffMillis / 1000) / 60;
            long hours = diffMinutes / 60;
            long minutes = diffMinutes % 60;

            Toast.makeText(this, "Cette alarme se déclenchera dans " + hours + " heures et " + minutes + " minutes", Toast.LENGTH_LONG).show();

            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();

            Intent intent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTime.getTimeInMillis(), pendingIntent);
        });
    }
}
