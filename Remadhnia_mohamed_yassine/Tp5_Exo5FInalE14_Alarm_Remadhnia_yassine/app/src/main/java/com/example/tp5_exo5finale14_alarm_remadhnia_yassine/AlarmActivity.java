package com.example.tp5_exo5finale14_alarm_remadhnia_yassine;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button startButton;
    private Chronometer chronometer;
    private Handler handler;
    private Runnable checkTimeRunnable;
    private long targetTimeMillis;
    private boolean alarmActive = false;
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timePicker = findViewById(R.id.timePicker);
        startButton = findViewById(R.id.startButton);
        chronometer = findViewById(R.id.chronometer);

        timePicker.setIs24HourView(true);

        handler = new Handler();

        createNotificationChannel();


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!alarmActive) {
                    startChronometer();
                } else {
                    stopAlarm();
                    resetChronometer();
                    startButton.setText(R.string.start_counter);
                    alarmActive = false;
                }
            }
        });


        if (getIntent().getBooleanExtra("notification_clicked", false)) {
            resetChronometer();
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "alarm_channel",
                    "Alarm Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Channel for Alarm notifications");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void startChronometer() {

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();


        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        int currentSecond = calendar.get(Calendar.SECOND);


        int totalTargetSeconds = hour * 3600 + minute * 60;
        int totalCurrentSeconds = currentHour * 3600 + currentMinute * 60 + currentSecond;


        int secondsUntilTarget = totalTargetSeconds - totalCurrentSeconds;


        if (secondsUntilTarget <= 0) {
            secondsUntilTarget += 24 * 3600;
        }


        long timeUntilAlarmMillis = secondsUntilTarget * 1000;


        targetTimeMillis = System.currentTimeMillis() + timeUntilAlarmMillis;


        int hoursUntilAlarm = secondsUntilTarget / 3600;
        int minutesUntilAlarm = (secondsUntilTarget % 3600) / 60;
        String message = getString(R.string.alarm_message, hoursUntilAlarm, minutesUntilAlarm);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();


        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();


        startButton.setText(R.string.stop_counter);
        alarmActive = true;


        checkTimeRunnable = new Runnable() {
            @Override
            public void run() {
                if (System.currentTimeMillis() >= targetTimeMillis && alarmActive) {

                    triggerAlarmActions();
                } else if (alarmActive) {

                    handler.postDelayed(this, 1000);
                }
            }
        };


        handler.post(checkTimeRunnable);
    }

    private void triggerAlarmActions() {

        Toast.makeText(this, R.string.time_up, Toast.LENGTH_LONG).show();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            VibratorManager vibratorManager = (VibratorManager) getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
            if (vibratorManager != null) {
                vibratorManager.getDefaultVibrator().vibrate(4000);
            }
        } else {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                vibrator.vibrate(4000);
            }
        }

        showNotification();


        chronometer.stop();
        alarmActive = false;
        startButton.setText(R.string.start_counter);
    }

    private void showNotification() {

        Intent notificationIntent = new Intent(this, AlarmActivity.class);
        notificationIntent.putExtra("notification_clicked", true);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "alarm_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(getString(R.string.alarm_title))
                .setContentText(getString(R.string.alarm_message_end))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

    private void stopAlarm() {

        if (checkTimeRunnable != null) {
            handler.removeCallbacks(checkTimeRunnable);
        }


        chronometer.stop();
    }

    private void resetChronometer() {
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancel(NOTIFICATION_ID);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);


        if (intent.getBooleanExtra("notification_clicked", true)) {
            resetChronometer();
            startButton.setText(R.string.start_counter);
            alarmActive = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (checkTimeRunnable != null) {
            handler.removeCallbacks(checkTimeRunnable);
        }
    }
}