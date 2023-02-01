package solid.icon.english.architecture.firebase.database.operations

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import solid.icon.english.architecture.firebase.database.interfaces.OnGetDataListener
import solid.icon.english.architecture.room.App
import solid.icon.english.architecture.room.SubTopicModel

class RecipientOperation {

    var list: List<String> = ArrayList()

    public fun getAllData(key: String, topicsName: String) {

    }

    private fun insertNewSubTopics(topicsName: String) {
        val subTopicDao = App.getInstance().database.subTopicDao()
        for (s in list) {
            val subTopicModel = SubTopicModel()
            subTopicModel.topicsName = topicsName
            subTopicModel.subTopicsName = s
            subTopicDao!!.insert(subTopicModel)
        }
    }

    private fun getAllData(key: String?, listener: OnGetDataListener) {
        val ref = FirebaseDatabase.getInstance().getReference(key!!).child("subNames")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataSnapshot1 in dataSnapshot.children) {
                    val subName = dataSnapshot1.value as String?
                    list.plus(subName)
                }
                listener.onSuccess(dataSnapshot)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

}