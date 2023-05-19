package solid.icon.english.navigation_menu.account.authentication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import solid.icon.english.R
import solid.icon.english.architecture.parents.ActivityGlobal
import solid.icon.english.databinding.AuthActivityBinding
import solid.icon.english.dialogs.AddingDialog
import solid.icon.english.navigation_menu.account.profile.AccountActivity
import solid.icon.english.navigation_menu.account.registration.RegistrationActivity

class AuthActivity : ActivityGlobal() {

    private lateinit var viewModel: AccountViewModel
    private val auth = FirebaseAuth.getInstance()
    private lateinit var binding: AuthActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showActionBar(true, "Account")

        lifecycleScope.launch {
            delay(800)
            viewModel = AccountViewModel(this@AuthActivity)
            withContext(Dispatchers.Main) {
                initUI()
                binding.linearLayout.animate().alpha(1f).duration = 500
            }
        }
    }

    private fun initUI() {
        var user = auth.currentUser
        if (user != null) {
            binding.etEmail.setText(user.email)
        }

        binding.btnLogIn.setOnClickListener {
            if (!doesInternetConnectionExist())
                return@setOnClickListener
            hideSoftKeyboard(binding.etPassword)
            viewModel.logIn(getEmail(), getPassword(),
                onStart = {
                    binding.loadingLayout.root.isVisible = true
                },
                onSuccess = {
                    binding.loadingLayout.root.isVisible = false
                    if (it) {
                        user = auth.currentUser
                        viewModel.saveEmail(user!!.email, user!!.uid)
                        Toasty.success(context, getString(R.string.logged_in)).show()
                        lifecycleScope.launch {
                            delay(800)
                            goToActivity(AccountActivity::class.java)
                            delay(1000)
                            finish()
                        }
                    } else {
                        Toasty.error(context, "email or password is not valid").show()
                    }
                })
        }

        binding.btnForgetPassword.setOnClickListener {
            showForgetPasswordDialog()
        }

        binding.btnDoNotHaveAccount.setOnClickListener {
            goToActivity(RegistrationActivity::class.java)
            lifecycleScope.launch {
                delay(1000)
                finish()
            }
        }

//        binding.btnLogOut.setOnClickListener {
//            if (!doesInternetConnectionExist())
//                return@setOnClickListener
//            viewModel.logOut {
//                binding.etEmail.setText("")
//                binding.etPassword.setText("")
//                viewModel.saveEmail("")
//                Toasty.success(context, getString(R.string.logged_out)).show()
//            }
//        }

        binding.tvOurSite.setOnClickListener {
            val uri: Uri = Uri.parse("http://english-vs.web.app")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun showForgetPasswordDialog() {
        val dialog = AddingDialog(context)
        dialog.setTitle("Write your email")
        val editText = dialog.getEditText()
        editText.hint = "email"

        dialog.setPositiveButton("Next") {
            hideSoftKeyboard(editText)
            val email = editText.text.toString().trim()
            if (email.isNotBlank()) {
                viewModel.forgetPassword(email)
                Toasty.info(context, "Електронний лист для скидання пароля надіслано на $email")
                    .show()
            } else
                Toasty.warning(context, "Email field is empty").show()
        }

        dialog.setNegativeButton("Cancel") {}
        dialog.show()
    }

    private fun getEmail(): String? {
        val email = binding.etEmail.text.toString().trim()
        return email.ifBlank {
            Toasty.error(context, getString(R.string.email_field_is_empty)).show()
            null
        }
    }

    private fun getPassword(): String? {
        val email = binding.etPassword.text.toString().trim()
        return email.ifBlank {
            Toasty.error(context, getString(R.string.password_field_is_empty)).show()
            null
        }
    }
}