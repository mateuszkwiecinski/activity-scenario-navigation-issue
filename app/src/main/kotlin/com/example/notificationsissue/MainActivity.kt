package com.example.notificationsissue

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_a)

        findViewById<Button>(R.id.btn)
            .setOnClickListener { view -> view.context.showNotification() }
        findViewById<TextView>(R.id.tv_title)
            .text = intent?.dataString ?: "No data"
    }
}


private fun Context.showNotification() {
    createChannel()
    val notification = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle("Foo Title")
        .setContentText("Bar Content")
        .setAutoCancel(true)
        .setContentIntent(
            PendingIntent.getActivity(
                this,
                0,
                Intent(Intent.ACTION_VIEW)
                    .setClass(this, TrampolineActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
            )
        )
        .build()

    NotificationManagerCompat.from(this)
        .notify(21_37, notification)
}

fun Context.createChannel() {
    NotificationManagerCompat.from(this)
        .createNotificationChannel(
            NotificationChannel(
                channelId,
                "Stub channel name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
                .apply { description = "stub channel description" }
        )
}


private const val channelId = "stub-channel-id"
