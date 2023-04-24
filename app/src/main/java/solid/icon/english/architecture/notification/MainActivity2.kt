package solid.icon.english.architecture.notification

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main2.*
import solid.icon.english.R

class MainActivity2 : AppCompatActivity() {

    //NOTE:
    // + 1. подумать на счёт подписки на топики и не морочится с базой и токенами
    // + 2. если решу делать только подписку - то пересмотреть видео и сделать так как Филип
    // + 3. протестить это всё на Notification VS
    // + 4. сделать тестовую версию тут
    // + 5. В итоге добавить подписку, тогда когда пользователь добавляет тему по ключу
    // 6. протестить и отправить сообщение на тему
    // 7. сделать кнопку в менюхе над подтемами (по умолчанию invisible)
    // 8. открывать её если юзер = владелец и имеет доступ к редактированию
    // 9. кинуть на кнопку уже протестированный функционал

    val TAG = "firebasenotifications"
    val firebaseService = FirebaseService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            firebaseService.token = it
            etToken.setText(it)
            Log.e(TAG, it)
        }
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC + "text") //NOTE: sub-notification

        btnSend.setOnClickListener {
            val title = etTitle.text.toString()
            val message = etMessage.text.toString()
            val recipientToken = etToken.text.toString()
            if (title.isNotEmpty() && message.isNotEmpty() && recipientToken.isNotEmpty()) {
                PushNotification(
                    NotificationData(title, message),
                    TOPIC
                ).also {
                    //sendNotification(it)
                }
            }
        }
    }


}