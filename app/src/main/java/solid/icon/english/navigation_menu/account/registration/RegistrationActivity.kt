package solid.icon.english.navigation_menu.account.registration

import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import solid.icon.english.architecture.parents.ActivityGlobal
import solid.icon.english.databinding.RegistrationActivityBinding
import solid.icon.english.navigation_menu.account.authentication.AuthActivity
import solid.icon.english.navigation_menu.account.models.UserProfileItem
import solid.icon.english.navigation_menu.account.profile.AccountActivity
import java.text.SimpleDateFormat
import java.util.*

class RegistrationActivity : ActivityGlobal() {

    private lateinit var viewModel: RegistrationViewModel
    private val auth = FirebaseAuth.getInstance()
    private lateinit var binding: RegistrationActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showActionBar(true, "Registration")

        lifecycleScope.launch {
            delay(800)
            viewModel = RegistrationViewModel(this@RegistrationActivity)
            withContext(Dispatchers.Main) {
                initUI()
                binding.linearLayout.animate().alpha(1f).duration = 500
            }
        }
    }

    private fun initUI() {
        binding.btnRegistration.setOnClickListener {
            if (!doesInternetConnectionExist())
                return@setOnClickListener
            getAllDataFromFields()
        }

        binding.btnAlreadyHaveAcc.setOnClickListener {
            goToActivity(AuthActivity::class.java)
        }
    }

    private fun getAllDataFromFields() {
        val userProfileItem = validateField() ?: return
        val password = binding.etPassword.text.toString().trim()
        if (password.isBlank()) {
            Toasty.warning(context, getToastText("password")).show()
            return
        }
        viewModel.register(
            userProfileItem, password,
            onStart = {
                binding.loadingLayout.root.isVisible = true
            },
            onSuccess = {
                binding.loadingLayout.root.isVisible = false
                if (it) {
                    viewModel.saveEmail(userProfileItem.email, auth.uid!!)
                    goToActivity(AccountActivity::class.java)
                }
            })
    }

    private fun validateField(): UserProfileItem? {
        val userName = binding.etName.text.toString().trim()
        val surname = binding.etSurname.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val birthday = binding.etBirthday.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()

        if (userName.isBlank()) {
            Toasty.warning(context, getToastText("userName")).show()
            return null
        } else if (surname.isBlank()) {
            Toasty.warning(context, getToastText("surname")).show()
            return null
        } else if (phone.length < 10) {
            Toasty.warning(context, getToastText("phone")).show()
            return null
        } else if (birthday.isBlank()) {
            Toasty.warning(context, getToastText("birthday")).show()
            return null
        } else if (email.isBlank()) {
            Toasty.warning(context, getToastText("email")).show()
            return null
        }

        Log.e(TAG, "validateField: $userName")

        return UserProfileItem(
            userName,
            surname,
            phone,
            birthday,
            email,
            false,
            getCurrentTime(),
            "empty",
            "empty"
        )
    }

    private fun getToastText(whichField: String): String {
        return when (whichField) {
            "phone", "email" -> {
                "$whichField is not valid"
            }
            else -> "$whichField is empty"
        }
    }

    private fun getCurrentTime(): String {
        val currentTime = Date()
        val format = SimpleDateFormat("dd.MM.yyyy, HH:mm:ss", Locale.getDefault())
        return format.format(currentTime)
    }

    override fun goToActivity(toActivity: Class<*>?) {
        lifecycleScope.launch {
            super.goToActivity(toActivity)
            delay(1000)
            finish()
        }
    }
}