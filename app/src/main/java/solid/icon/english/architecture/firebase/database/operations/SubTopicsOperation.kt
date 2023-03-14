package solid.icon.english.architecture.firebase.database.operations

import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SubTopicsOperation {

    fun moveSubTopics(subTopicsName: String, dataSnapshot: DataSnapshot) {
        GlobalScope.launch {
            dataSnapshot.ref.child("subNames").push().setValue(subTopicsName)
        }
    }

    fun deleteSubTopics(subTopicsName: String, dataSnapshot: DataSnapshot) {
        val validatedSub = FirebaseOperation().validateKey(subTopicsName)
        GlobalScope.launch {
            for (dataSnapshot1 in dataSnapshot.child("subNames").children) {
                val subNameSnapshot = dataSnapshot1.value as String?
                if (subNameSnapshot == subTopicsName) {
                    dataSnapshot1.ref.removeValue()
                    dataSnapshot.child("subTopics").child(validatedSub).ref.removeValue()
                }
            }
        }
    }

    fun deleteSubTopicsNames(dataSnapshot: DataSnapshot) {
        GlobalScope.launch {
            dataSnapshot.child("subNames").ref.removeValue()
        }
    }
}