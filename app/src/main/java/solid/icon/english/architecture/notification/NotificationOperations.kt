package solid.icon.english.architecture.notification

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import solid.icon.english.architecture.local_data.PreferencesOperations
import java.util.*

class NotificationOperations {

    private val preOperations = PreferencesOperations()

    fun pushMessageToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.e("pushMessageToken", "isSuccessful")
                Log.e("pushMessageToken", token)
                FirebaseDatabase.getInstance().getReference("/users/${preOperations.uid}/token")
                    .setValue(token)
            } else {
                Log.e("firstOpen", "not isSuccessful")
            }
        }
    }

    //NOTE: this bread
    fun sendNotification() {
        Log.e("sendNotification", "Start sendNotification")
        // Получаем токен ученика из Firebase Realtime Database
        val token =
            "fjfA8ktgQmiyQMQEHuU_tn:APA91bEnR85hZh9URf2JNkR27i-el_gRpCdzIq9iASe_CiiurD8snlaP-vgNN0TFHtO_PfUDYlB_q0rkcnCrqRdcHTO2sJABkD0tJRYMyW2G8SyUgj9O44nIb_MoaOoCiLhg3AMMDKHk"

        // Отправляем уведомление с использованием FCM
        val message = RemoteMessage.Builder(token)
            .setMessageId(UUID.randomUUID().toString())
            .setData(mapOf("title" to "-NQfnvCnZ_V-SV2dhNPg", "body" to "University"))
            .build()
        val response = FirebaseMessaging.getInstance().send(message)
        // Response is a message ID string.
        Log.e("sendNotification", "Finish $response")

    }

}