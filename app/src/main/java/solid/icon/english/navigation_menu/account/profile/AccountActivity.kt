package solid.icon.english.navigation_menu.account.profile

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import solid.icon.english.architecture.parents.ActivityGlobal
import solid.icon.english.databinding.AccountActivityBinding
import solid.icon.english.navigation_menu.account.authentication.AuthActivity
import solid.icon.english.navigation_menu.account.timetable.TimetableActivity


class AccountActivity : ActivityGlobal() {

    private lateinit var viewModel: AccountViewModel
    private val auth = FirebaseAuth.getInstance()
    private lateinit var binding: AccountActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccountActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showActionBar(true, "Account")

        lifecycleScope.launch {
            delay(800)
            viewModel = AccountViewModel(this@AccountActivity)
            withContext(Dispatchers.Main) {
                initUI()
                binding.linearLayout.animate().alpha(1f).duration = 500
            }
        }
    }

    private fun initUI() {
        binding.tvEmail.text = auth.currentUser?.email

        binding.btnShowTimetable.setOnClickListener {
            if (!doesInternetConnectionExist())
                return@setOnClickListener
            goToActivity(TimetableActivity::class.java)
        }

        binding.btnShowBudget.setOnClickListener {
            if (!doesInternetConnectionExist())
                return@setOnClickListener

        }

        binding.btnChangePassword.setOnClickListener {
            if (!doesInternetConnectionExist())
                return@setOnClickListener
            auth.sendPasswordResetEmail(auth.currentUser!!.email!!)
        }

        binding.btnSignOut.setOnClickListener {
            if (!doesInternetConnectionExist())
                return@setOnClickListener
            viewModel.logOut {
                lifecycleScope.launch {
                    goToActivity(AuthActivity::class.java)
                    delay(1000)
                    finish()
                }

            }
        }
    }
}