package solid.icon.english.architecture.local_data

import android.util.Log
import androidx.preference.PreferenceManager
import solid.icon.english.architecture.gpt.GPT
import solid.icon.english.architecture.room.App

class PreferencesOperations {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(App.instance)
    private val editor = preferences.edit()
    private val currentDateKey = GPT().giveDateKey()
    var email: String? = null
        set(value) {
            editor.putString(Keys.EMAIL.key, value)
            editor.apply()
            field = value
        }
        get() = preferences.getString(Keys.EMAIL.key, null)

    var uid: String = ""
        set(value) {
            editor.putString(Keys.UID.key, value)
            editor.apply()
            field = value
        }
        get() = preferences.getString(Keys.UID.key, "null")!!

    enum class Keys(val key: String) {
        IS_FIRST_OPEN("is firstly open"),
        IS_OPENED_SITE("is opened site"),
        NUMBER_OF_VISITS("number of visits"),
        EMAIL("email"),
        UID("uid"),
        ALLOWED_TOPICS("number of topics"),
        PITCH("pitch"),
        SPEECH_RATE("speechRate"),
        GPT_DESCRIPTION("gpt description")
    }

    //--------------method calls when user open app only first time----------//
    fun firstOpen() {
        putGptCalls()
        val number = getNumberOfVisits()

        if (preferences.getBoolean(Keys.IS_FIRST_OPEN.key, true)) {
            editor.putBoolean(Keys.IS_FIRST_OPEN.key, false)
            editor.putInt(Keys.ALLOWED_TOPICS.key, 3) //free posting topics
        }
        editor.putInt(Keys.NUMBER_OF_VISITS.key, number + 1)
        editor.apply()
    }

    fun setIsOpenedSite(isOpen: Boolean){
        editor.putBoolean(Keys.IS_OPENED_SITE.key, isOpen)
        editor.apply()
    }

    fun getIsOpenedSite(): Boolean{
        return preferences.getBoolean(Keys.IS_OPENED_SITE.key, false)
    }

    fun getNumberOfVisits(): Int{
        return preferences.getInt(Keys.NUMBER_OF_VISITS.key, 0)
    }

    fun getAllowedTopics(): Int {
        return preferences.getInt(Keys.ALLOWED_TOPICS.key, 0)
    }

    fun savePitchAndSpeech(pitch: Float, speechRate: Float) {
        editor.putFloat(Keys.PITCH.key, pitch)
        editor.putFloat(Keys.SPEECH_RATE.key, speechRate)
        editor.apply()
    }

    fun increaseAllowedTopics(number: Int = 1) {
        val sharedNum = preferences.getInt(Keys.ALLOWED_TOPICS.key, 0)
        editor.putInt(Keys.ALLOWED_TOPICS.key, sharedNum + number)
        editor.apply()
    }

    fun decreaseAllowedTopics(number: Int = 1) {
        val sharedNum = preferences.getInt(Keys.ALLOWED_TOPICS.key, 0)
        Log.e("sharedNum", sharedNum.toString())
        editor.putInt(Keys.ALLOWED_TOPICS.key, sharedNum - number)
        editor.apply()
    }

    private fun putGptCalls() {
        val freeCalls = 20
        val errorNumber = -99
        val gptCallsNumber = preferences.getInt(currentDateKey, errorNumber)
        if (gptCallsNumber == errorNumber) {
            editor.putInt(currentDateKey, freeCalls)
            editor.apply()
        }
    }

    fun decreaseGptCalls() {
        val sharedNum = preferences.getInt(currentDateKey, 0)
        Log.d("decreaseGptCalls", "$sharedNum minus 1 = ${sharedNum - 1}")
        editor.putInt(Keys.ALLOWED_TOPICS.key, sharedNum - 1)
        editor.apply()
    }

    fun getGptCalls(): Int {
        return preferences.getInt(currentDateKey, 0)
    }

    fun getCheckingSubTopics(mod_key: String): Boolean {
        return preferences.getBoolean(mod_key, false)
    }

    fun putGptDescription() {
        editor.putBoolean(Keys.GPT_DESCRIPTION.key, true)
        editor.apply()
    }

    fun getGptDescription(): Boolean {
        return preferences.getBoolean(Keys.GPT_DESCRIPTION.key, false)
    }

}