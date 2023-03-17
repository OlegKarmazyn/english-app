package solid.icon.english.architecture.firebase.database.operations

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import solid.icon.english.architecture.room.App

class TopicsOperation {

    fun moveTopics(topicsName: String, email: String, uid: String) {
        GlobalScope.launch {
            val dbRef = FirebaseDatabase.getInstance().reference.child("users_topics").push()
            val topicModelDao = App.getInstance().database.topicModelDao()
            val topicModel = topicModelDao!!.getByTopicsName(topicsName)
            topicModel.topicsKey = dbRef.key
            topicModelDao.upsert(topicModel)
            dbRef.child("topicsName").setValue(topicsName)
            dbRef.child("email").setValue(email)
            dbRef.child("uid").setValue(uid)
        }
    }

    fun deleteTopics(dataSnapshot: DataSnapshot) {
        GlobalScope.launch {
            dataSnapshot.ref.removeValue()
        }
    }

}