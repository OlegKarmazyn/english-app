package solid.icon.english.architecture.local_data

import androidx.preference.PreferenceManager
import solid.icon.english.architecture.gpt.GPT
import solid.icon.english.architecture.room.App

class PreferencesOperations {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(App.instance)
    private val editor = preferences.edit()
    private val currentDateKey = GPT().giveDateKey()

    enum class Keys(val key: String) {
        IS_FIRST_OPEN("is firstly open"),
        EMAIL("email"),
        ALLOWED_TOPICS("number of topics"),
        PITCH("pitch"),
        SPEECH_RATE("speechRate"),
        GPT_DESCRIPTION("gpt description")
    }

    fun firstOpen() {
        putGptCalls()
        if (preferences.getBoolean(Keys.IS_FIRST_OPEN.key, true)) {
            editor.putBoolean(Keys.IS_FIRST_OPEN.key, false)
            editor.putInt(Keys.ALLOWED_TOPICS.key, 1) // TODO: make it for gptBot
            editor.apply()
        }
    }

    fun saveEmail(email: String) {
        editor.putString(Keys.EMAIL.key, email)
        editor.apply()
    }

    fun getEmail(): String? {
        return preferences.getString(Keys.EMAIL.key, null)
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

    fun decreaseGptCalls(){
        val sharedNum = preferences.getInt(currentDateKey, 0)
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

    fun getGptDescription() : Boolean{
        return preferences.getBoolean(Keys.GPT_DESCRIPTION.key, false)
    }

}