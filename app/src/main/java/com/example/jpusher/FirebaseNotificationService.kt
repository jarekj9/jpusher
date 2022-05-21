package com.example.jpusher

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseNotificationService : FirebaseMessagingService() {

    //override fun onBind(intent: Intent): IBinder {
       // TODO("Return the communication channel to the service.")
   // }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")
        var body = remoteMessage.notification?.body
        if (!body.isNullOrEmpty()) {
                Log.d(TAG, "Message data payload: ${body}")
                broadcastMessage(body)
            }
        }

    private fun broadcastMessage(msg: String?) {
        val bundle = Bundle()
        bundle.putString("msgBody", msg);
        val newIntent = Intent()
        newIntent.action = "ACTION_STRING_ACTIVITY"
        newIntent.putExtra("msg", bundle)
        sendBroadcast(newIntent)
    }


}


