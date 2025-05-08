package com.example.alarme;

import com.example.alarme.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.app.PendingIntent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(
                context,
                context.getString(R.string.toast_elapsed),
                Toast.LENGTH_LONG
        ).show();

        Vibrator vib =
                (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vib.vibrate(
                    VibrationEffect.createOneShot(4000, VibrationEffect.DEFAULT_AMPLITUDE)
            );
        } else {
            vib.vibrate(4000);
        }

        Intent notifIntent = new Intent(context, MainActivity.class);
        notifIntent.setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP
        );
        PendingIntent pending = PendingIntent.getActivity(
                context, 0, notifIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                        .setContentTitle(
                                context.getString(R.string.notification_title)
                        )
                        .setContentText(
                                context.getString(R.string.notification_message)
                        )
                        .setContentIntent(pending)
                        .setAutoCancel(true);

        NotificationManagerCompat.from(context)
                .notify(1, builder.build());
    }
}