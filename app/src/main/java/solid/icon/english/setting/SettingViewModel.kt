package solid.icon.english.setting

import androidx.lifecycle.ViewModel

class SettingViewModel(context: SettingsActivity) : ViewModel() {

    private val authModel = AuthModel(context)

    fun signUp(email: String?, password: String?) {
        // Create a new user with email and password
        if (!email.isNullOrBlank() && !password.isNullOrBlank()) {
            authModel.signUp(email, password)
        }
    }

    fun logIn(email: String?, password: String?, onSuccess: () -> Unit) {
        // log into system
        if (!email.isNullOrBlank() && !password.isNullOrBlank()) {
            authModel.logIn(email, password, onSuccess)
        }
    }

    fun logOut(onSuccess: () -> Unit) {
        authModel.logOut(onSuccess)
    }
}