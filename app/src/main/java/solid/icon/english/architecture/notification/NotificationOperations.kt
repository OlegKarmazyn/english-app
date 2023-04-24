package solid.icon.english.architecture.notification

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationOperations {

    private val TAG = "NotificationOperations"

    fun sendNotification(key: String, title: String) {
        sendNotification(key, title, "There is new changes")
    }

    fun sendNotification(
        key: String,
        title: String,
        message: String
    ) {
        if (title.isNotEmpty() && message.isNotEmpty()) {
            PushNotification(
                NotificationData(title, message),
                TOPIC + key
            ).also {
                sendNotification(it)
            }
        }
    }

    private fun sendNotification(notification: PushNotification) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.e(TAG, "START sendNotification")
                val response = RetrofitInstance.api.postNotification(notification)
                if (response.isSuccessful) {
                    Log.d(TAG, "Response: $response")
                } else {
                    Log.e(TAG, response.errorBody().toString())
                }
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        }
}