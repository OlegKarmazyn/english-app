package solid.icon.english.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.settings_activity.*
import solid.icon.english.R
import solid.icon.english.architecture.parents.ActivityGlobal


class SettingsActivity : ActivityGlobal() {

    private lateinit var viewModel: SettingViewModel
    private val auth = FirebaseAuth.getInstance()

    private var isUserExist: Boolean = false
        set(value) {
            if (value) {
                etEmail.isEnabled = false
                etPassword.isVisible = false
                btnLogIn.isVisible = false
                btnLogOut.isVisible = true
            } else {
                etEmail.isEnabled = true
                etPassword.isVisible = true
                btnLogIn.isVisible = true
                btnLogOut.isVisible = false
            }
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        showActionBar(true, "Account")

        viewModel = SettingViewModel(this)
        initUI()
    }

    private fun initUI() {
        var user = auth.currentUser
        if (user != null) {
            etEmail.setText(user.email)
            if (user.isEmailVerified)
                isUserExist = user.isEmailVerified
            else
                Toasty.warning(context, "Please check ${user.email} for a verification link").show()
        }

        btnLogIn.setOnClickListener {
            viewModel.logIn(getEmail(), getPassword(),
                onStart = {
                    loading_layout.isVisible = true
                },
                onSuccess = {
                    isUserExist = it
                    loading_layout.isVisible = false
                    if (it) {
                        user = auth.currentUser
                        viewModel.saveEmail(user!!.email, user!!.uid)
                        Toasty.success(context, getString(R.string.logged_in)).show()
                    }
                })
        }

        btnLogOut.setOnClickListener {
            viewModel.logOut {
                isUserExist = false
                etEmail.setText("")
                etPassword.setText("")
                viewModel.saveEmail("")
                Toasty.success(context, getString(R.string.logged_out)).show()
            }
        }

        tvOurSite.setOnClickListener {
            val uri: Uri = Uri.parse("http://english-vs.web.app")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun getEmail(): String? {
        val email = etEmail.text.toString().trim()
        return email.ifBlank {
            Toasty.error(context, getString(R.string.email_field_is_empty)).show()
            null
        }
    }

    private fun getPassword(): String? {
        val email = etPassword.text.toString().trim()
        return email.ifBlank {
            Toasty.error(context, getString(R.string.password_field_is_empty)).show()
            null
        }
    }
}