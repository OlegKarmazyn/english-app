package solid.icon.english.architecture.firebase.database.operations

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import solid.icon.english.architecture.firebase.database.WordFB
import solid.icon.english.architecture.firebase.database.interfaces.GetTopicsModelListener
import solid.icon.english.architecture.firebase.database.interfaces.OnGetDataListener
import solid.icon.english.architecture.firebase.database.interfaces.OnSuccessListener
import solid.icon.english.architecture.local_data.PreferencesOperations
import solid.icon.english.architecture.room.TopicModel

class FirebaseOperation {

    companion object {
        private const val TAG = "FirebaseOperation"
    }

    private val topicsOperation = TopicsOperation()
    private val subTopicsOperation = SubTopicsOperation()
    private val wordsOperation = WordsOperation()
    private val recipientOperation = RecipientOperation()
    private val senderOperation = SenderOperation(this)
    private val preferencesOperations = PreferencesOperations()

    // NOTE: get permission to change data if owner of it
    private fun getPathIfAllowed(topicsName: String, listener: OnGetDataListener) {
        val ref = FirebaseDatabase.getInstance().reference.child("users_topics")
        val topicsQuery = ref.orderByChild("topicsName").equalTo(topicsName)
        topicsQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataSnapshot1 in dataSnapshot.children) {
                    val hashMap = dataSnapshot1.value as HashMap<*, *>
                    val checkingEmail = hashMap["email"] as String
                    if (checkingEmail == preferencesOperations.email) {
                        listener.onSuccess(dataSnapshot1)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException())
            }
        })
    }

    fun validateKey(s: String): String {
        return s.map { if (it in ".#\$[]/") "_" else it }.joinToString("")
    }

    // NOTE: Get All Data from Firebase DB
    fun getTopicsWithAllData(key: String, listener: GetTopicsModelListener) {
        getDataSnapshotByKey(key) { dataSnapshot: DataSnapshot ->
            val topicsName = dataSnapshot.child("topicsName").getValue(String::class.java)
            val topicModel = TopicModel()
            topicModel.topicsName = topicsName
            topicModel.topicsKey = key
            topicModel.country = "en"
            listener.onSuccess(topicModel)
            getDataUnderTopic(key, topicsName!!) //main method for getting data
        }
    }

    private fun getDataUnderTopic(key: String, topicsName: String) {
        recipientOperation.getAllData(key, topicsName)
    }

    fun getDataSnapshotByKey(key: String, listener: OnGetDataListener) {
        val ref = FirebaseDatabase.getInstance().reference.child("users_topics").child(key)
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listener.onSuccess(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    // NOTE: Post All Data to Firebase DB
    fun postData(topicsName: String) {
        senderOperation.postAllData(topicsName)
    }

    fun uploadDate(topicsName: String, onSuccessListener: OnSuccessListener) {
        getPathIfAllowed(topicsName) {
            subTopicsOperation.deleteSubTopicsNames(it)
            senderOperation.uploadAllData(topicsName)
            onSuccessListener.onSuccess()
        }
    }

    // region Topics
    fun moveTopics(topicsName: String) {
        preferencesOperations.email?.let {
            topicsOperation.moveTopics(topicsName, it, preferencesOperations.uid)
        }
    }

    fun deleteTopics(topicsName: String) {
        getPathIfAllowed(topicsName) { dataSnapshot: DataSnapshot ->
            topicsOperation.deleteTopics(dataSnapshot)
        }
    }
    //endregion

    // region Sub topics
    fun moveSubTopics(topicsName: String, subTopicsName: String) {
        getPathIfAllowed(topicsName) { dataSnapshot: DataSnapshot ->
            subTopicsOperation.moveSubTopics(subTopicsName, dataSnapshot)
        }
    }

    fun deleteSubTopics(topicsName: String, subTopicsName: String) {
        getPathIfAllowed(topicsName) { dataSnapshot: DataSnapshot ->
            subTopicsOperation.deleteSubTopics(subTopicsName, dataSnapshot)
        }
    }
    //endregion

    // region Words
    fun moveWord(topicsName: String, subTopicsName: String, wordFB: WordFB) {
        val subKey = validateKey(subTopicsName)
        val wordKey = validateKey(wordFB.englishWord)
        getPathIfAllowed(topicsName) { dataSnapshot: DataSnapshot ->
            wordsOperation.moveWord(subKey, wordKey, wordFB, dataSnapshot)
        }
    }

    fun updateWord(
        previousName: String,
        topicsName: String,
        subTopicsName: String,
        wordFB: WordFB
    ) {
        val subKey = validateKey(subTopicsName)
        val wordKey = validateKey(wordFB.englishWord)
        val previousKey = validateKey(previousName)
        getPathIfAllowed(topicsName) { dataSnapshot: DataSnapshot ->
            wordsOperation.updateWord(previousKey, subKey, wordKey, wordFB, dataSnapshot)
        }
    }

    fun deleteWord(topicsName: String, subTopicsName: String, englishWord: String) {
        val subKey = validateKey(subTopicsName)
        val wordKey = validateKey(englishWord)
        getPathIfAllowed(topicsName) { dataSnapshot: DataSnapshot ->
            wordsOperation.deleteWord(subKey, wordKey, dataSnapshot)
        }
    }
    //endregion
}