package solid.icon.english.architecture.firebase.database.operations

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import solid.icon.english.architecture.firebase.StaticData
import solid.icon.english.architecture.firebase.database.WordFB
import solid.icon.english.architecture.firebase.database.interfaces.GetTopicsModelListener
import solid.icon.english.architecture.firebase.database.interfaces.OnGetDataListener
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

    /* --------------get permission to change data if owner of it------------------- */
    fun getPathIfAllowed(topicsName: String, listener: OnGetDataListener) {
        val ref = FirebaseDatabase.getInstance().reference
        val topicsQuery = ref.orderByChild("topicsName").equalTo(topicsName)
        topicsQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataSnapshot1 in dataSnapshot.children) {
                    val hashMap = dataSnapshot1.value as HashMap<*, *>
                    val checkingEmail = hashMap["email"] as String
                    if (checkingEmail == StaticData.email) {
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
        val arr = s.split("").toTypedArray()
        var key = ""
        for (ch in arr) {
            if (ch == "." || ch == "#" || ch == "$" || ch == "[" || ch == "]") {
                key += "_"
            } else {
                key += ch
            }
        }
        return key
    }

    /* --------------------------------Get All Data from Firebase DB---------------------------- */
    fun getTopicsWithAllData(key: String, listener: GetTopicsModelListener) {
        getDataSnapshotByKey(key) { dataSnapshot: DataSnapshot ->
            val topicsName = dataSnapshot.child("topicsName").getValue(String::class.java)
            val topicModel = TopicModel()
            topicModel.topicsName = topicsName
            topicModel.topicsKey = key
            topicModel.country = "en"
            listener.onSuccess(topicModel)
            getAllData(key, topicsName!!) //main method for getting data
        }
    }

    private fun getAllData(key: String, topicsName: String) {
        recipientOperation.getAllData(key, topicsName)
    }

    fun getDataSnapshotByKey(key: String, listener: OnGetDataListener) {
        val ref = FirebaseDatabase.getInstance().getReference(key)
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listener.onSuccess(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    /* --------------------------------Post All Data to Firebase DB--------------------------- */
    fun postData(topicsName: String){
        senderOperation.postAllData(topicsName)
    }

    /* ------------------------------------Topics---------------------------- */
    fun moveTopics(topicsName: String) { // TODO: 01.02.2023 check if email exists
        topicsOperation.moveTopics(topicsName)
    }

    fun deleteTopics(topicsName: String) {
        getPathIfAllowed(topicsName) { dataSnapshot: DataSnapshot ->
            topicsOperation.deleteTopics(dataSnapshot)
        }
    }

    /* ------------------------------------Sub topics---------------------------- */
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

    /* ------------------------------------Words---------------------------- */
    fun moveWord(topicsName: String, subTopicsName: String, wordFB: WordFB) {
        val subKey = validateKey(subTopicsName)
        val wordKey = validateKey(wordFB.englishWord)
        getPathIfAllowed(topicsName) { dataSnapshot: DataSnapshot ->
            wordsOperation.moveWord(subKey, wordKey, wordFB, dataSnapshot)
        }
    }

    fun updateWord(previousName: String, topicsName: String, subTopicsName: String, wordFB: WordFB) {
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
}