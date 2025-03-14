package ru.example.a1403

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

object NotificationUtil {
    fun CreateNotificationChannel(context: Context){
        val notification = NotificationChannel("MainActivity", "MainActivity" , NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notification)
    }

    fun notifyNotification(context:Context, title: String, text:String){
        val intentBtn = Intent(context, MainActivity::class.java)
        intentBtn.apply{
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context,
            0,
            intentBtn,
            PendingIntent.FLAG_IMMUTABLE)
        val notificationBtn  = NotificationCompat.Builder(context, "MainActivity")
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(androidx.core.R.drawable.ic_call_answer_video)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(context)){
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) { }
            notify(1, notificationBtn.build())
        }
    }
}