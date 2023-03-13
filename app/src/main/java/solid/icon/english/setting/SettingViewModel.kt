package solid.icon.english.setting

import androidx.lifecycle.ViewModel

class SettingViewModel(context: SettingsActivity) : ViewModel() {

    private val authModel = AuthModel(context)

    fun logIn(
        email: String?,
        password: String?,
        onStart: () -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        // log into system
        if (!email.isNullOrBlank() && !password.isNullOrBlank()) {
            authModel.logIn(email, password, onStart, onSuccess)
        }
    }

    fun logOut(onSuccess: () -> Unit) {
        authModel.logOut(onSuccess)
    }
}