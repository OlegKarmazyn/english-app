package solid.icon.english.navigation_menu.account.registration

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import solid.icon.english.architecture.parents.ActivityGlobal
import solid.icon.english.databinding.RegistrationBinding
import solid.icon.english.navigation_menu.account.AccountViewModel

class RegistrationActivity : ActivityGlobal() {

    private lateinit var viewModel: AccountViewModel
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
            //viewModel = AccountViewModel(this@RegistrationActivity)
            withContext(Dispatchers.Main) {
                initUI()
                binding.linearLayout.animate().alpha(1f).duration = 500
            }
        }
    }

    private fun initUI() {
        val user = auth.currentUser

        binding.btnRegistration.setOnClickListener {
            if (!doesInternetConnectionExist())
                return@setOnClickListener
            //todo
        }

        binding.btnAlreadyHaveAcc.setOnClickListener {
            if (!doesInternetConnectionExist())
                return@setOnClickListener

        }
    }
}