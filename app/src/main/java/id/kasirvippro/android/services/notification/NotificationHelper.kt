package id.kasirvippro.android.services.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.provider.Settings
import androidx.core.app.NotificationCompat
import id.kasirvippro.android.R


class NotificationHelper(private val mContext: Context) {
    private var mNotificationManager: NotificationManager? = null
    private var mBuilder: NotificationCompat.Builder? = null

    /**
     * Create and push the notification
     */
    fun createNotification(title: String, message: String, bitmap: Bitmap?, intent: Intent,
                           notificationChannelId: String, notificationChannelName: String) {
        /**Creates an explicit intent for an Activity in your app */
        val resultIntent = intent
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val resultPendingIntent = PendingIntent.getActivity(mContext,
                0 /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        mBuilder = NotificationCompat.Builder(mContext, notificationChannelId)
        mBuilder?.setSmallIcon(R.drawable.ic_logo_white)
        mBuilder?.setContentTitle(title)
                ?.setContentText(message)
                ?.setAutoCancel(true)
                ?.setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                ?.setContentIntent(resultPendingIntent)

        if(bitmap != null){
            mBuilder?.setLargeIcon(bitmap)
            mBuilder?.setStyle(NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap))
        }

        mNotificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(notificationChannelId, notificationChannelName, importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            mBuilder?.setChannelId(notificationChannelId)
            mNotificationManager?.createNotificationChannel(notificationChannel)
        }

        mNotificationManager?.notify(0 /* Request Code */, mBuilder!!.build())
    }

    /*companion object {
        val NOTIFICATION_CHANNEL_ID = "10001"
    }*/
}
