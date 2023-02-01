package solid.icon.english.architecture.firebase.database.operations

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import solid.icon.english.architecture.firebase.database.interfaces.OnGetDataListener
import solid.icon.english.architecture.room.App
import solid.icon.english.architecture.room.SubTopicDao
import solid.icon.english.architecture.room.SubTopicModel

class RecipientOperation {

    fun getAllData(key: String, topicsName: String) {
        FirebaseOperation().getDataSnapshotByKey(key) { dataSnapshot ->
            getSubTopics(topicsName, dataSnapshot)
        }
    }

    private fun getSubTopics(topicsName: String, dataSnapshot: DataSnapshot) {
        for (dataSnap in dataSnapshot.child("subNames").children) {
            val subName = dataSnap.value as String?
            insertNewSubTopics(topicsName, subName!!)
        }
    }

    private fun insertNewSubTopics(topicsName: String, subTopicsName: String) {
        val subTopicDao = App.getInstance().database.subTopicDao()
        val subTopicModel = SubTopicModel()
        subTopicModel.topicsName = topicsName
        subTopicModel.subTopicsName = subTopicsName
        subTopicDao!!.insert(subTopicModel)
    }

    private fun getWords(dataSnapshot: DataSnapshot) {

    }


}