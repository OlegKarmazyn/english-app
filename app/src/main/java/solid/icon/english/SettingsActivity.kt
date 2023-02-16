package solid.icon.english

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.preference.PreferenceManager
import es.dmoral.toasty.Toasty
import solid.icon.english.architecture.parents.ActivityGlobal

class SettingsActivity : ActivityGlobal() {

    private lateinit var etEmail: EditText
    private lateinit var etPitch: EditText
    private lateinit var etSpeechRate: EditText
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        showActionBar(true, "")

        initUI()
    }

    private fun initUI() {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)

        etEmail = findViewById(R.id.email)
        etPitch = findViewById(R.id.etPitch)
        etSpeechRate = findViewById(R.id.etSpeechRate)
        etEmail.setText(getEmailText())
        etPitch.setText(getPitchText())
        etSpeechRate.setText(getSpeechRateText())
        findViewById<Button>(R.id.saveEmail).setOnClickListener {
            saveEmail()
            savePitchAndSpeech()
            Toasty.success(context, "Data saved").show()
        }
    }

    private fun getEmailText(): String {
        return preferences.getString("email", "admin@gmail.com")!!
    }

    private fun getPitchText(): String {
        return preferences.getFloat("pitch", 0.7f).toString()
    }

    private fun getSpeechRateText(): String {
        return preferences.getFloat("speechRate", 0.7f).toString()
    }

    private fun saveEmail() {
        val email = etEmail.text.toString().trim()
        val editor = preferences.edit()
        editor.putString("email", email)
        editor.apply()
    }

    private fun savePitchAndSpeech() {
        try {
            val pitch = etPitch.text.toString().trim().toFloat()
            val speechRate = etSpeechRate.text.toString().trim().toFloat()
            val editor = preferences.edit()
            editor.putFloat("pitch", pitch)
            editor.putFloat("speechRate", speechRate)
            editor.apply()
        }catch (_: java.lang.Exception){}

    }

}