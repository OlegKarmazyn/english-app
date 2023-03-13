package solid.icon.english.setting

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import es.dmoral.toasty.Toasty
import solid.icon.english.architecture.firebase.database.interfaces.OnSuccessListener

class AuthModel(private val ctx: Activity) {

    private val auth = FirebaseAuth.getInstance()

    fun signUp(email: String, password: String) {
        // Create a new user with email and password
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(ctx) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    // Send verification email
                    user?.sendEmailVerification()?.addOnCompleteListener { task2 ->
                        if (task2.isSuccessful) {
                            Toasty.info(
                                ctx,
                                "Verification email sent to $email"
                            ).show()
                        } else {
                            Toasty.error(
                                ctx,
                                "Failed to send verification email."
                            ).show()
                        }
                    }
                } else {
                    Toasty.error(ctx, "Sign up failed.").show()
                }
            }
    }

    fun logIn(email: String, password: String, onSuccess: () -> Unit ) {
        // Sign in with email and password
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(ctx) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null && user.isEmailVerified) {

                        Log.e("AuthModel", "user.isEmailVerified")
                        onSuccess()

                    } else {
                        // User has not verified their email
                        Toasty.warning(ctx, "Please check your email for a verification link.")
                            .show()
                        auth.signOut()
                    }
                } else {
                    // Sign in failed
                    Toasty.error(ctx, "Authentication failed.").show()
                }
            }
    }

    fun logOut(onSuccess: () -> Unit) {
        auth.signOut()
        onSuccess()
    }

}