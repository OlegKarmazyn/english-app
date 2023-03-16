package solid.icon.english.setting

import androidx.lifecycle.ViewModel
import solid.icon.english.architecture.local_data.PreferencesOperations

class SettingViewModel(context: SettingsActivity) : ViewModel() {

    private val authModel = AuthModel(context)
    private val preferencesOperations = PreferencesOperations()

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

    fun saveEmail(email: String){
        preferencesOperations.email = email
    }
}