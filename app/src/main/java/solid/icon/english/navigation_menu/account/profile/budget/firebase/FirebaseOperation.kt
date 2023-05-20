package solid.icon.english.navigation_menu.account.profile.budget.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import solid.icon.english.navigation_menu.account.profile.budget.interfaces.OnGetListOfHistory

class FirebaseOperation {

    private val mainReference = FirebaseDatabase.getInstance().reference.child("budget")
    private val info = "info"
    private val history = "history"

    fun getPaidLessons(key: String, onGetPaidLessons: (Int) -> Unit) {
        mainReference.child(key).child(info)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userInfo = snapshot.getValue(UserInfoModel::class.java)
                    if (userInfo != null)
                        onGetPaidLessons(userInfo.paidLessons!!)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    fun getListOfHistory(key: String, listListener: OnGetListOfHistory) {
        listListener.onStart()
        val list: MutableList<HistoryModel> = mutableListOf()
        mainReference.child(key).child(history)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (snap in snapshot.children) {
                        val userInfo = snap.getValue(HistoryModel::class.java)
                        list.add(userInfo!!)
                    }
                    listListener.onSuccess(list)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}