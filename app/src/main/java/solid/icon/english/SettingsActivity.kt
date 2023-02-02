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
        etEmail.setText(getEmailText())
        findViewById<Button>(R.id.saveEmail).setOnClickListener {
            saveEmail()
        }
    }

    private fun getEmailText(): String {
        return preferences.getString("email", "admin@gmail.com")!!
    }

    private fun saveEmail() {
        val email = etEmail.text.toString().trim()
        val editor = preferences.edit()
        editor.putString("email", email)
        editor.apply()
        Toasty.success(context, "Email saved").show()
    }

}