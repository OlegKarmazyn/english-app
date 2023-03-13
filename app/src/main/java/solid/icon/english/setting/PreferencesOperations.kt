package solid.icon.english.setting

import android.content.Context
import androidx.preference.PreferenceManager

class PreferencesOperations(context: Context) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveEmail(email: String) {
        val editor = preferences.edit()
        editor.putString("email", email)
        editor.apply()
    }

    private fun savePitchAndSpeech(pitch: Float, speechRate: Float) {
        val editor = preferences.edit()
        editor.putFloat("pitch", pitch)
        editor.putFloat("speechRate", speechRate)
        editor.apply()
    }

}