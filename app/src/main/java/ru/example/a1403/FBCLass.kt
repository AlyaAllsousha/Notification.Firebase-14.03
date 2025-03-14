package ru.example.a1403

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FBCLass:FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if(message.notification != null){
            NotificationUtil.CreateNotificationChannel(context = this)
            NotificationUtil.notifyNotification(this, message.notification!!.title.toString(), message.notification!!.body.toString())
        }
    }
}