package solid.icon.english.navigation_menu.account.profile

import androidx.lifecycle.ViewModel
import solid.icon.english.architecture.local_data.PreferencesOperations
import solid.icon.english.navigation_menu.account.models.AuthModel

class AccountViewModel(context: AccountActivity) : ViewModel() {

    private val authModel = AuthModel(context)
    private val preferencesOperations = PreferencesOperations()

    fun logOut(onSuccess: () -> Unit) {
        preferencesOperations.email = ""
        preferencesOperations.uid = ""
        authModel.logOut(onSuccess)
    }
}