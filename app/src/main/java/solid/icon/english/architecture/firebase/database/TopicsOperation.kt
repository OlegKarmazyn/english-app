package solid.icon.english.architecture.firebase.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import solid.icon.english.architecture.firebase.StaticData.email
import solid.icon.english.architecture.room.App

class TopicsOperation(private var firebaseOperation: FirebaseOperation) {


    fun moveTopics(topicsName: String) {
        GlobalScope.launch {
            val dbRef = FirebaseDatabase.getInstance().reference.push()
            val topicModelDao = App.getInstance().database.topicModelDao()
            val topicModel = topicModelDao!!.getByTopicsName(topicsName)
            topicModel.topicsKey = dbRef.key
            topicModelDao.update(topicModel)
            dbRef.child("topicsName").setValue(topicsName)
            dbRef.child("email").setValue(email)
        }
    }

    fun deleteTopics(topicsName: String) {
        GlobalScope.launch {
            firebaseOperation.getPathIfAllowed(topicsName) { dataSnapshot: DataSnapshot ->
                dataSnapshot.ref.removeValue()
            }
        }
    }

}