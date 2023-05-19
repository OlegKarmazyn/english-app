package solid.icon.english.navigation_menu.account

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import es.dmoral.toasty.Toasty

class AuthModel(private val ctx: Activity) {

    private val auth = FirebaseAuth.getInstance()

    fun logIn(email: String, password: String, onStart: () -> Unit, onSuccess: (Boolean) -> Unit) {
        onStart()
        Log.e("logIn -", "signInWithEmailAndPassword")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(ctx) { task ->
                //note: login user
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        Log.e("AuthModel", "user.isEmailVerified")
                        onSuccess(true)
                    } else {
                        // User has not verified their email
                        Toasty.warning(ctx, "Error: please re-auth")
                            .show()
                        auth.signOut()
                        onSuccess(false)
                    }
                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(ctx) { createTask ->
                            //note: create user
                            if (createTask.isSuccessful) {
                                Toasty.info(
                                    ctx,
                                    "Created $email successfully"
                                ).show()
                            } else {
                                Toasty.error(ctx, "Sign up failed").show()
                            }
                            onSuccess(createTask.isSuccessful)
                        }
                }
            }
    }

    fun logOut(onSuccess: () -> Unit) {
        auth.signOut()
        onSuccess()
    }
}