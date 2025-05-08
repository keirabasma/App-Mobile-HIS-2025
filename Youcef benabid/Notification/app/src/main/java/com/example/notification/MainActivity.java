package com.example.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private Chronometer chronometer;
    private Button startButton;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private boolean isRunning = false;
    private long pauseOffset = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.chronometer);
        timePicker = findViewById(R.id.timePicker);
        startButton = findViewById(R.id.startButton);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker.setIs24HourView(true);
        startButton.setOnClickListener(v -> {
            if (!isRunning) {
                startChronometer();
                startButton.setText("Stop");
                isRunning = true;
            } else {
                stoptChronometer();
                startButton.setText("Start");
                isRunning = false;
            }
        });




}

    private void stoptChronometer() {
        chronometer.stop();
        pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
    }

    private void startAlarm() {
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

    }

    private void startChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
        chronometer.start();
    }






    private void stopAlarm() {
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());

        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAlarm();
    }

}




