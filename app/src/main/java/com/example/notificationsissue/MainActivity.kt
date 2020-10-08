package com.example.notificationsissue

import android.app.*
import android.content.*
import android.os.*
import androidx.appcompat.app.*
import androidx.navigation.*

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Register the channel with the system
            notificationManager.createNotificationChannel(NotificationChannel(
                channelId,
                "Stub channel name",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "stub channel description" }
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        findNavController(R.id.nav_host).handleDeepLink(intent)
    }

    companion object {
        const val channelId = "stub-channel-id"
    }
}
