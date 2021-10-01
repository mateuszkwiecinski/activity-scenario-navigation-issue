package com.example.notificationsissue

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private val notificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel()
        setContentView(R.layout.activity_main)
    }

    private fun createNotificationChannel() {
        // Register the channel with the system
        notificationManager.createNotificationChannel(
            NotificationChannel(
                channelId,
                "Stub channel name",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "stub channel description" }
        )
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        findNavController(R.id.nav_host).handleDeepLink(intent)
    }

    companion object {
        const val channelId = "stub-channel-id"
    }
}
