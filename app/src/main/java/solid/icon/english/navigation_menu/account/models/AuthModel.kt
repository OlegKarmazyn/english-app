package solid.icon.english.navigation_menu.account.models

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
                        Log.e("AuthModel", "signInWithEmailAndPassword")
                    } else {
                        // User has not verified their email
                        Toasty.warning(ctx, "Error: please re-auth")
                            .show()
                        auth.signOut()
                    }
                    onSuccess(task.isSuccessful)
                }
            }
    }

    fun logOut(onSuccess: () -> Unit) {
        auth.signOut()
        onSuccess()
    }
}