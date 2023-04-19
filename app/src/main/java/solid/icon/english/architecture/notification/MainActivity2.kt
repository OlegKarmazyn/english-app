package solid.icon.english.architecture.notification

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import solid.icon.english.R

const val TOPIC = "TOPICS" //NOTE: this must be key of topic ("-" and 19 chars)

class MainActivity2 : AppCompatActivity() {

    //NOTE:
    // 1. подумать на счёт подписки на топики и не морочится с базой и токенами
    // 2. если решу делать только подписку - то пересмотреть видео и сделать так как Филип
    // 3. протестить это всё на Notification VS
    // 4. сделать тестовую версию тут
    // 5. В итоге добавить подписку, тогда когда пользователь добавляет тему по ключу
    // 6. протестить и отправить сообщение на тему
    // 7. сделать кнопку в менюхе над подтемами (по умолчанию invisible)
    // 8. открывать её если юзер = владелец и имеет доступ к редактированию
    // 9. кинуть на кнопку уже протестированный функционал

    val TAG = "firebasenotifications"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            FirebaseService.token = it
            etToken.setText(it)
            Log.e(TAG, it)
        }
//        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC) //NOTE: This MAIN WAY

        btnSend.setOnClickListener {
            val title = etTitle.text.toString()
            val message = etMessage.text.toString()
            val recipientToken = etToken.text.toString()
            if (title.isNotEmpty() && message.isNotEmpty() && recipientToken.isNotEmpty()) {
                PushNotification(
                    NotificationData(title, message),
                    recipientToken
                ).also {
                    sendNotification(it)
                }
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