package solid.icon.english.setting

import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.preference.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.settings_activity.*
import solid.icon.english.R
import solid.icon.english.architecture.parents.ActivityGlobal

class SettingsActivity : ActivityGlobal() {

    private lateinit var preferences: SharedPreferences
    private lateinit var viewModel: SettingViewModel
    private val auth = FirebaseAuth.getInstance()

    private var isUserExist: Boolean = false
        set(value) {
            if (value) {
                etPassword.isVisible = false
                btnLogIn.isVisible = false
                btnLogOut.isVisible = true
            } else {
                etPassword.isVisible = true
                btnLogIn.isVisible = true
                btnLogOut.isVisible = false
            }
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        showActionBar(true, "Account")

        initUI()
    }

    private fun initUI() {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        viewModel = SettingViewModel(this)
        val user = auth.currentUser
        if (user != null) {
            etEmail.setText(user.email)
            if (user.isEmailVerified)
                isUserExist = user.isEmailVerified
            else
                Toasty.warning(context, "Please check your email for a verification link").show()
        }

        btnLogIn.setOnClickListener {
            viewModel.logIn(getEmail(), getPassword(),
                onStart = {
                    loading_layout.isVisible = true
                },
                onSuccess = {
                    isUserExist = it
                    loading_layout.isVisible = false
                    if (it) {
                        saveEmail(getEmail()!!)
                        Toasty.success(context, "Log in - done").show()
                    }
                })
        }

        btnLogOut.setOnClickListener {
            viewModel.logOut {
                isUserExist = false
                etEmail.setText("")
                etPassword.setText("")
                saveEmail("")
                Toasty.success(context, "Log out - done").show()
            }
        }
    }

    private fun getEmail(): String? {
        val email = etEmail.text.toString().trim()
        return email.ifBlank {
            Toasty.error(context, "Email field is empty").show()
            null
        }
    }

    private fun getPassword(): String? {
        val email = etPassword.text.toString().trim()
        return email.ifBlank {
            Toasty.error(context, "Password field is empty").show()
            null
        }
    }

    private fun saveEmail(email: String) {
        val editor = preferences.edit()
        editor.putString("email", email)
        editor.apply()
    }
}

//    private fun savePitchAndSpeech() {
//        try {
//            val pitch = etPitch.text.toString().trim().toFloat()
//            val speechRate = etSpeechRate.text.toString().trim().toFloat()
//            val editor = preferences.edit()
//            editor.putFloat("pitch", pitch)
//            editor.putFloat("speechRate", speechRate)
//            editor.apply()
//        }catch (_: java.lang.Exception){}
//    }

