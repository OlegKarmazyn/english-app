package solid.icon.english.navigation_menu.account.models

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import es.dmoral.toasty.Toasty

class RegistrationModel(private val ctx: Activity) {

    private val auth = FirebaseAuth.getInstance()

    fun register(
        email: String,
        password: String,
        userProfileItem: UserProfileItem,
        onStart: () -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        onStart()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(ctx) { createTask ->
                //note: create user
                if (createTask.isSuccessful) {
                    Toasty.info(
                        ctx,
                        "Created $email successfully"
                    ).show()
                    postUserProfile(userProfileItem)
                } else {
                    Toasty.error(ctx, "Created account failed").show()
                }
                onSuccess(createTask.isSuccessful)
            }
    }

    private fun postUserProfile(userProfileItem: UserProfileItem) {
        val database = FirebaseDatabase.getInstance()
        auth.uid?.let { uid ->
            database.getReference("users").child(uid).setValue(userProfileItem)
        }
    }
}