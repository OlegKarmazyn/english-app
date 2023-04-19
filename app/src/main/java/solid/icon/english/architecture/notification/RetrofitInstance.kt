package solid.icon.english.architecture.notification

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import solid.icon.english.architecture.notification.Constants.Companion.BASE_URL

class RetrofitInstance {

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: NotificationAPI by lazy { //note
            retrofit.create(NotificationAPI::class.java)
        }
    }
}