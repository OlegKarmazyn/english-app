package solid.icon.english.navigation_menu.account.profile

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import solid.icon.english.architecture.parents.ActivityGlobal
import solid.icon.english.databinding.AccountActivityBinding
import solid.icon.english.navigation_menu.account.registration.RegistrationViewModel

class AccountActivity : ActivityGlobal() {

    private lateinit var viewModel: RegistrationViewModel
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
//            viewModel = RegistrationViewModel(this@RegistrationActivity)
//            withContext(Dispatchers.Main) {
//                initUI()
//                binding.linearLayout.animate().alpha(1f).duration = 500
//            }
        }
    }

    private fun initUI() {
//        binding.btnRegistration.setOnClickListener {
//            if (!doesInternetConnectionExist())
//                return@setOnClickListener
//            getAllDataFromFields()
//        }
//
//        binding.btnAlreadyHaveAcc.setOnClickListener {
//            if (!doesInternetConnectionExist())
//                return@setOnClickListener
//            goToActivity(AuthActivity::class.java)
//        }
    }
}