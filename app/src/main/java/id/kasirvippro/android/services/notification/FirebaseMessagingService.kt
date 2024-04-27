package id.kasirvippro.android.services.notification

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession


class FirebaseMessagingService : FirebaseMessagingService() {
    private var TAG = "FirebaseMessagingService"
    private var notificationManager: NotificationManager? = null
    lateinit var notificationHelper: NotificationHelper
    lateinit var appSession: AppSession
    private lateinit var broadcaster: LocalBroadcastManager

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationHelper = NotificationHelper(applicationContext)
        appSession = AppSession()
        broadcaster = LocalBroadcastManager.getInstance(applicationContext)
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        appSession.setSharedPreferenceString(AppConstant.DEVICE_TOKEN, p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        //Verify if the message contains data
        val data = remoteMessage.data
        if (data.isNotEmpty()) {
            Log.d(TAG, "Message data : " + remoteMessage.data)
//            {status=reject, type=claim, message=tidak valid
            handleNotificationByType(remoteMessage)
        }
    }

    private fun handleNotificationByType(remoteMessage: RemoteMessage) {

        val data = remoteMessage.data
        if (data.isNullOrEmpty()) {
            return
        }


    }


    private fun createNotification(remoteMessage: RemoteMessage?, resultIntent: Intent) {
        if (remoteMessage?.notification != null) {
            val message = remoteMessage.data["message"] ?: ""
            val title = remoteMessage.notification?.title ?: ""
            val body = remoteMessage.notification?.body ?: message
            Log.d(TAG, "Message body : $body , title : $title")
            try {
                notificationHelper.createNotification(
                    "Your Store", body, null, resultIntent,
                    AppConstant.NOTIFICATION_CHANNEL_ID_DEFAULT, AppConstant.NOTIFICATION_CHANNEL_NAME_DEFAULT
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("ERROR_MS", "${e.message}")
            }
        }
    }


}