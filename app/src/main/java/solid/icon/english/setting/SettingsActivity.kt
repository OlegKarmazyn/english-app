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
                btnSignUp.isVisible = false
                btnLogIn.isVisible = false
                btnLogOut.isVisible = true
            } else {
                etPassword.isVisible = true
                btnSignUp.isVisible = true
                btnLogIn.isVisible = true
                btnLogOut.isVisible = false
                etEmail.setText("")
                etPassword.setText("")
            }
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        showActionBar(true, "")

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
                Toasty.warning(context, "Please check your email for a verification link.").show()
        }

        // TODO: make loading
        btnSignUp.setOnClickListener {
            viewModel.signUp(getEmail(), getPassword())
        }

        btnLogIn.setOnClickListener {
            viewModel.logIn(getEmail(), getPassword()) {
                isUserExist = true
            }
        }

        btnLogOut.setOnClickListener {
            viewModel.logOut(){
                isUserExist = false
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

}

//    private fun saveEmail() {
//        val email = email
//        val editor = preferences.edit()
//        editor.putString("email", email())
//        editor.apply()
//    }

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

