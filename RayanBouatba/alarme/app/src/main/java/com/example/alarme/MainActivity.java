package com.example.alarme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "alarm_channel";
    private TimePicker timePicker;
    private Chronometer chronometer;
    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        timePicker = findViewById(R.id.timePicker);
        chronometer = findViewById(R.id.chronometer);
        buttonStart = findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(v -> {
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();
            long now = SystemClock.elapsedRealtime();
            long delay = (hour * 60 + minute) * 60 * 1000L;

            chronometer.setBase(now);
            chronometer.start();

            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(
                    this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            am.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, now + delay, pi);

            String msg = getString(
                    R.string.toast_delay,
                    hour, minute
            );
            android.widget.Toast.makeText(
                    this, msg, android.widget.Toast.LENGTH_LONG
            ).show();
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Alarme Channel";
            String description = "Canal pour notifications alarme";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
    }
}