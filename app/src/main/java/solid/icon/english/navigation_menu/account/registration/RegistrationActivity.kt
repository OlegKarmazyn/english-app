package solid.icon.english.navigation_menu.account.registration

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import solid.icon.english.architecture.parents.ActivityGlobal
import solid.icon.english.databinding.RegistrationBinding
import solid.icon.english.navigation_menu.account.authentication.AuthActivity
import solid.icon.english.navigation_menu.account.models.UserProfileItem
import java.text.SimpleDateFormat
import java.util.*

class RegistrationActivity : ActivityGlobal() {

    private lateinit var viewModel: RegistrationViewModel
    private val auth = FirebaseAuth.getInstance()
    private lateinit var binding: RegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationBinding.inflate(layoutInflater)
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
            if (!doesInternetConnectionExist())
                return@setOnClickListener
            goToActivity(AuthActivity::class.java)
        }
    }

    private fun getAllDataFromFields() {
        val userProfileItem = validateField() ?: return
        val password = binding.etPassword.toString().trim()
        if (password.isBlank()) {
            showToast("password")
            return
        }
        viewModel.register(
            userProfileItem, password,
            onStart = {
                binding.loadingLayout.root.isVisible = true
            },
            onSuccess = {
                if (it)
                    viewModel.saveEmail(userProfileItem.email, auth.uid!!)
                binding.loadingLayout.root.isVisible = false
                lifecycleScope.launch {
                    delay(500)
                    goToActivity(AccounActivity::class)
                }
            })
    }

    private fun validateField(): UserProfileItem? {
        val userName = binding.etName.toString().trim()
        val surname = binding.etSurname.toString().trim()
        val phone = binding.etPhone.toString().trim()
        val birthday = binding.etBirthday.toString().trim()
        val email = binding.etEmail.toString().trim()

        if (userName.isBlank()) {
            showToast("userName")
            return null
        } else if (surname.isBlank()) {
            showToast("surname")
            return null
        } else if (phone.length < 10) {
            showToast("phone")
            return null
        } else if (birthday.isBlank()) {
            showToast("birthday")
            return null
        } else if (email.isBlank()) {
            showToast("email")
            return null
        }

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

    private fun showToast(whichField: String): String {
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

}