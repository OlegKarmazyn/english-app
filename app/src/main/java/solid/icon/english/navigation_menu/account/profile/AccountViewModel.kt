package solid.icon.english.navigation_menu.account.profile

import androidx.lifecycle.ViewModel
import solid.icon.english.architecture.local_data.PreferencesOperations
import solid.icon.english.navigation_menu.account.models.AuthModel
import solid.icon.english.navigation_menu.account.models.GetBudgetKeyModel

class AccountViewModel(context: AccountActivity) : ViewModel() {

    private val authModel = AuthModel(context)
    private val getBudgetKeyModel = GetBudgetKeyModel()
    private val preferencesOperations = PreferencesOperations()

    fun logOut(onSuccess: () -> Unit) {
        preferencesOperations.email = ""
        preferencesOperations.uid = ""
        authModel.logOut(onSuccess)
    }

    fun getBudgetKey(uid: String, onGetKey: (String) -> Unit) {
        getBudgetKeyModel.getBudgetKey(uid, onGetKey)
    }
}