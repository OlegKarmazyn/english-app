package solid.icon.english.navigation_menu.account.registration

import androidx.lifecycle.ViewModel
import solid.icon.english.architecture.local_data.PreferencesOperations
import solid.icon.english.navigation_menu.account.models.RegistrationModel
import solid.icon.english.navigation_menu.account.models.UserProfileItem

class RegistrationViewModel(context: RegistrationActivity) : ViewModel() {

    private val registrationModel = RegistrationModel(context)
    private val preferencesOperations = PreferencesOperations()

    fun register(
        userProfileItem: UserProfileItem,
        password: String,
        onStart: () -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        if (!userProfileItem.email.isNullOrBlank()) {
            registrationModel.register(
                userProfileItem.email!!,
                password,
                userProfileItem,
                onStart,
                onSuccess
            )
        }
    }

    fun saveEmail(email: String?, uid: String = "") {
        preferencesOperations.email = email
        preferencesOperations.uid = uid
    }
}