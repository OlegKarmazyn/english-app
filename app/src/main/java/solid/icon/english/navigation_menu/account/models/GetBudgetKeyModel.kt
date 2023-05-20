package solid.icon.english.navigation_menu.account.models

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GetBudgetKeyModel {

    fun getBudgetKey(uid: String, onGetKey: (String) -> Unit) {
        val database = FirebaseDatabase.getInstance()
        val userRef = database.reference.child("users").child(uid)
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userData = dataSnapshot.getValue(UserProfileItem::class.java)
                val budgetKey = userData?.budget_key
                if (budgetKey != null) {
                    onGetKey(budgetKey)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}