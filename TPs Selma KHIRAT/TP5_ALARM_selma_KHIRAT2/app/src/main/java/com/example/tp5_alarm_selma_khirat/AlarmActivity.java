package com.example.tp5_alarm_selma_khirat;




import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    TimePicker timePicker;
    Chronometer chronometer;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        chronometer = findViewById(R.id.chronometer);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            Calendar future = Calendar.getInstance();
            future.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            future.set(Calendar.MINUTE, timePicker.getMinute());
            future.set(Calendar.SECOND, 0);

            if (future.before(now)) {
                future.add(Calendar.DATE, 1); // lendemain
            }

            long diffMillis = future.getTimeInMillis() - now.getTimeInMillis();
            long diffSeconds = diffMillis / 1000;
            int hours = (int) (diffSeconds / 3600);
            int minutes = (int) ((diffSeconds % 3600) / 60);

            Toast.makeText(this, "Cette alarme se déclenchera dans " + hours + " heures et " + minutes + " minutes", Toast.LENGTH_LONG).show();

            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();

            Intent intent = new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + diffMillis, pendingIntent);
        });
    }
}
