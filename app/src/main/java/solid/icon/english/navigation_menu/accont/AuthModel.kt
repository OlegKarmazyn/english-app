package solid.icon.english.navigation_menu.accont

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
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    if (currentUser != null && currentUser.isEmailVerified) {
                        Log.e("AuthModel", "user.isEmailVerified")
                        onSuccess(true)
                    } else {
                        // User has not verified their email
                        Toasty.warning(ctx, "Please check your email for a verification link")
                            .show()
                        auth.signOut()
                        onSuccess(false)
                    }
                } else {
                    Log.e("logIn -", "createUserWithEmailAndPassword")
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(ctx) { createTask ->
                            if (createTask.isSuccessful) {
                                val newUser = auth.currentUser
                                // Send verification email
                                newUser?.sendEmailVerification()
                                    ?.addOnCompleteListener { emailTask ->
                                        if (emailTask.isSuccessful) {
                                            Toasty.info(
                                                ctx,
                                                "Verification email sent to $email"
                                            ).show()
                                        } else {
                                            Toasty.error(
                                                ctx,
                                                "Failed to send verification email"
                                            ).show()
                                        }
                                    }
                            } else {
                                Toasty.error(ctx, "Sign up failed").show()
                            }
                            onSuccess(false)
                        }
                }
            }
    }

    fun logOut(onSuccess: () -> Unit) {
        auth.signOut()
        onSuccess()
    }
}