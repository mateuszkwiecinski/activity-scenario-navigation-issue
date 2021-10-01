package com.example.notificationsissue

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment

class FragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_a, container, false).apply {
        findViewById<Button>(R.id.btn)
            .setOnClickListener { view -> view.context.showNotification() }
    }
}

private fun Context.showNotification() {
    val notification = NotificationCompat.Builder(this, MainActivity.channelId)
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle("Foo Title")
        .setContentText("Bar Content")
        .setAutoCancel(true)
        .setContentIntent(
            PendingIntent.getActivity(
                this,
                0,
                Intent(Intent.ACTION_VIEW)
                    .setClass(this, MainActivity::class.java)
                    .setData("http://app.com/fragmentB".toUri()),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
            )
        )
        .build()

    NotificationManagerCompat.from(this)
        .notify(21_37, notification)
}
