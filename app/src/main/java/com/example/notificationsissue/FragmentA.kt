package com.example.notificationsissue

import android.app.*
import android.content.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.core.app.*
import androidx.core.net.*
import androidx.fragment.app.Fragment

class FragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_a, container, false).apply {
        findViewById<Button>(R.id.btn).setOnClickListener {
            requireContext().showNotification()
        }
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
                    .setData("http://app.com/fragmentA/fragmentB".toUri()),
                PendingIntent.FLAG_ONE_SHOT
            )
        )
        .build()

    NotificationManagerCompat.from(this)
        .notify(21_37, notification)
}
