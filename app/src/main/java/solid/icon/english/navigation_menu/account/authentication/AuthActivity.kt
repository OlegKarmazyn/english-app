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
import solid.icon.english.databinding.AccountActivityBinding

class AuthActivity : ActivityGlobal() {

    private lateinit var viewModel: AccountViewModel
    private val auth = FirebaseAuth.getInstance()
    private lateinit var binding: AccountActivityBinding

    private var isUserExist: Boolean = false
        set(value) {
            if (value) {
                binding.etEmail.isEnabled = false
                binding.etPassword.isVisible = false
                binding.btnLogIn.isVisible = false
                binding.btnLogOut.isVisible = true
            } else {
                binding.etEmail.isEnabled = true
                binding.etPassword.isVisible = true
                binding.btnLogIn.isVisible = true
                binding.btnLogOut.isVisible = false
            }
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccountActivityBinding.inflate(layoutInflater)
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
            isUserExist = true
        }

        binding.btnLogIn.setOnClickListener {
            if (!doesInternetConnectionExist())
                return@setOnClickListener
            viewModel.logIn(getEmail(), getPassword(),
                onStart = {
                    binding.loadingLayout.root.isVisible = true
                },
                onSuccess = {
                    isUserExist = it
                    binding.loadingLayout.root.isVisible = false
                    if (it) {
                        user = auth.currentUser
                        viewModel.saveEmail(user!!.email, user!!.uid)
                        Toasty.success(context, getString(R.string.logged_in)).show()
                    }
                })
        }

        binding.btnLogOut.setOnClickListener {
            if (!doesInternetConnectionExist())
                return@setOnClickListener
            viewModel.logOut {
                isUserExist = false
                binding.etEmail.setText("")
                binding.etPassword.setText("")
                viewModel.saveEmail("")
                Toasty.success(context, getString(R.string.logged_out)).show()
            }
        }

        binding.tvOurSite.setOnClickListener {
            val uri: Uri = Uri.parse("http://english-vs.web.app")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
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